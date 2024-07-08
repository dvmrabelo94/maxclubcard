package br.com.diogenes.maxclubcard.entrypoint.api.v1.client.mapper;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.client.ClientOut;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientResponse;

public class ClientMapperEntrypoint {

    public static Client toClient(ClientRequest clientRequest) {
        return new Client(
                clientRequest.name(),
                clientRequest.document(),
                clientRequest.birth(),
                clientRequest.gender(),
                clientRequest.email(),
                clientRequest.phoneNumber()
        );
    }

    public static ClientResponse toClientResponse(ClientOut client) {
        return new ClientResponse(
                client.id(),
                client.name(),
                client.document(),
                client.birth(),
                client.gender(),
                client.email(),
                client.phoneNumber()
        );
    }
}
