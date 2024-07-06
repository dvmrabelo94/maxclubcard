package br.com.diogenes.maxclubcard.core.gateway;

import br.com.diogenes.maxclubcard.core.domain.client.Client;

public interface ClientGateway {

    void registerClient(Client client);
}
