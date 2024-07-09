package br.com.diogenes.maxclubcard.dataprovider.client;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.client.ClientOut;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.mapper.ClientMapper;
import br.com.diogenes.maxclubcard.dataprovider.client.repository.ClientRepository;
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

class ClientGatewayImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientGatewayImpl clientGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRegisterClientInRepositoryWhenRegisterClientIsCalled() {
        Client client = new Client("Joe", "1234", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455");
        ClientOut clientOut = new ClientOut(1l, "Joe", "1234", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455");

        when(clientRepository.save(any())).thenReturn(ClientMapper.toEntity(client));

        clientGateway.registerClient(client);

        verify(clientRepository).save(any());
    }

    @Test
    void shouldReturnClientOutWhenGetClientIsCalled() {
        ClientEntity clientEntity = new ClientEntity(1l, "Joe", "1234", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455");
        ClientOut clientOut = new ClientOut(1l, "Joe", "joe@mail.com", LocalDate.of(1990, 1, 1), "Masculino", "1234", "77112234455");

        when(clientRepository.findByDocument(any())).thenReturn(clientEntity);

        ClientOut result = clientGateway.getClient("1234");

        assertEquals(clientOut, result);
    }

    @Test
    void shouldReturnClientEntityWhenGetClientEntityIsCalled() {
        ClientEntity clientEntity = new ClientEntity(1L, "Joe", "1234", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455");

        when(clientRepository.findByDocument(any())).thenReturn(clientEntity);

        ClientEntity result = clientGateway.getClientEntity("1234");

        assertEquals(clientEntity, result);
    }
}