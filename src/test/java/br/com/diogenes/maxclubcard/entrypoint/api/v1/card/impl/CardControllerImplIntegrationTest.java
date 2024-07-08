package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.impl;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardResponse;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.facade.CardFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.config.name=application-test")
@AutoConfigureMockMvc
class CardControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardFacade cardFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterCard() throws Exception {
        CardRequest cardRequest = new CardRequest("1234567890123456", "12/23", "Credit", "Visa", "12312312312");
        CardResponse cardResponse = new CardResponse(1l, "1234567890123456", "12/23", "Credit", "Visa", "12312312312");

        when(cardFacade.registerCard(any(CardRequest.class))).thenReturn(cardResponse);

        String cardRequestJson = objectMapper.writeValueAsString(cardRequest);
        String cardResponseJson = objectMapper.writeValueAsString(cardResponse);

        mockMvc.perform(post("/v1/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cardRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(cardResponseJson));

        verify(cardFacade, times(1)).registerCard(any(CardRequest.class));
    }

}