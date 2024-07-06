package br.com.diogenes.maxclubcard.core.client.usecase;

import br.com.diogenes.maxclubcard.core.client.domain.Client;
import br.com.diogenes.maxclubcard.core.client.gateway.ClientGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientUseCase {

    private final ClientGateway clientGateway;

    public ClientUseCase(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }

    public void registerClient(Client client) {
        log.info("Registering client: " + client.name() + " " + client.document());
        clientGateway.registerClient(client);
        log.info("Client registered: " + client.name());
    }
}
