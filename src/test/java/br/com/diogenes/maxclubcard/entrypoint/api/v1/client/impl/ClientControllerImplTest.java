package br.com.diogenes.maxclubcard.entrypoint.api.v1.client.impl;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.facade.ClientFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ClientControllerImplTest {

    @Mock
    private ClientFacade clientFacade;

    @InjectMocks
    private ClientControllerImpl clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRegisterClientInFacadeWhenRegisterClientIsCalled() {
        ClientRequest clientRequest = new ClientRequest("Diogenes", "12312312312", LocalDate.of(1994, 2, 12), "Masculino", "rabelo@diogenes.com", "77112234455");
        clientController.registerClient(clientRequest);
        verify(clientFacade).registerClient(any(ClientRequest.class));
    }
}