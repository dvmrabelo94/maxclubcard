package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.impl;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionResponse;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.facade.TransactionFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.config.name=application-test")
@AutoConfigureMockMvc
class TransactionControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionFacade transactionFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGenerateLuckyNumber() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest( "123", "1234567890123456", BigDecimal.valueOf(300.00), LocalDateTime.now());
        TransactionResponse transactionResponse = new TransactionResponse(List.of(new LuckyNumber(UUID.randomUUID().toString(), true), new LuckyNumber(UUID.randomUUID().toString(), true)));

        when(transactionFacade.registerTransaction(any(TransactionRequest.class))).thenReturn(transactionResponse);

        String transactionRequestJson = objectMapper.writeValueAsString(transactionRequest);
        String transactionResponseJson = objectMapper.writeValueAsString(transactionResponse);

        mockMvc.perform(post("/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(transactionResponseJson));

        verify(transactionFacade, times(1)).registerTransaction(any(TransactionRequest.class));
    }
}