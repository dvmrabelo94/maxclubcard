package br.com.diogenes.maxclubcard.entrypoint.api.v1.client.impl;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientResponse;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.facade.ClientFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.config.name=application-test")
@AutoConfigureMockMvc
class ClientControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientFacade clientFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterClient() throws Exception {
        ClientRequest clientRequest = new ClientRequest("John Doe", "123456789", LocalDate.of(1990, 1, 1), "M", "john.doe@example.com", "1234567890");
        ClientResponse clientResponse = new ClientResponse(1L, "John Doe", "123456789", LocalDate.of(1990, 1, 1), "Masculino", "john.doe@example.com", "1234567890");

        when(clientFacade.registerClient(any(ClientRequest.class))).thenReturn(clientResponse);

        String clientRequestJson = objectMapper.writeValueAsString(clientRequest);
        String clientResponseJson = objectMapper.writeValueAsString(clientResponse);

        mockMvc.perform(post("/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(clientResponseJson));

        verify(clientFacade, times(1)).registerClient(any(ClientRequest.class));
    }

}