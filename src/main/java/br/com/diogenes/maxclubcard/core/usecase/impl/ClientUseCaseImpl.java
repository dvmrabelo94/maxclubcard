package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.client.ClientOut;
import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import br.com.diogenes.maxclubcard.core.usecase.ClientUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientUseCaseImpl implements ClientUseCase {

    private final ClientGateway clientGateway;

    public ClientUseCaseImpl(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }

    @Override
    public ClientOut registerClient(Client client) {
        log.info("Registering client: " + client.name() + " " + client.document());
        return clientGateway.registerClient(client);
    }
}
