package br.com.diogenes.maxclubcard.core.gateway;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.client.ClientOut;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;

public interface ClientGateway {

    ClientOut registerClient(Client client);

    ClientEntity getClientEntity(String cpf);

    ClientOut getClient(String cpf);
}
