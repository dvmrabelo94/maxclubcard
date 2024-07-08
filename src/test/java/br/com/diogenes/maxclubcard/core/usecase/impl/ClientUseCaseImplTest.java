package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.client.ClientOut;
import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientUseCaseImplTest {

    @Mock
    private ClientGateway clientGateway;

    @InjectMocks
    private ClientUseCaseImpl clientUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRegisterClientInGatewayWhenRegisterClientIsCalled() {
        Client client = new Client("John Doe", "123456789", LocalDate.of(1990, 1,1), "Masculino", "joe@mail.com", "77112234455");
        ClientOut clientOut = new ClientOut(1l, "John Doe", "123456789", LocalDate.of(1990, 1,1), "Masculino", "joe@mail.com", "77112234455");

        when(clientGateway.registerClient(any())).thenReturn(clientOut);

        clientUseCase.registerClient(client);

        verify(clientGateway).registerClient(any());
    }
}