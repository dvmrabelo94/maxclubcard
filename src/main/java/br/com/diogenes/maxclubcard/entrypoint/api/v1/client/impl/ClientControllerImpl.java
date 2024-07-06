package br.com.diogenes.maxclubcard.entrypoint.api.v1.client.impl;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.ClientController;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientControllerImpl implements ClientController {

    @Override
    public ResponseEntity<ClientResponse> registerClient(ClientRequest clientRequest) {
        return ResponseEntity.ok(new ClientResponse());
    }
}
