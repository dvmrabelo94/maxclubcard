package br.com.diogenes.maxclubcard.entrypoint.api.v1.client.facade;

import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import br.com.diogenes.maxclubcard.core.usecase.ClientUseCase;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientResponse;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.mapper.ClientMapperEntrypoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientFacade {

    private final ClientUseCase clientUseCase;

    public ClientFacade(final ClientUseCase clientUseCase) {
        this.clientUseCase = clientUseCase;
    }

    public ClientResponse registerClient(ClientRequest clientRequest) {
        var client = ClientMapperEntrypoint.toClient(clientRequest);
        return ClientMapperEntrypoint.toClientResponse(clientUseCase.registerClient(client));
    }
}
