package br.com.diogenes.maxclubcard.dataprovider.client;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.client.ClientOut;
import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.repository.ClientRepository;
import br.com.diogenes.maxclubcard.dataprovider.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientGatewayImpl implements ClientGateway {

    private final ClientRepository clientRepository;

    public ClientGatewayImpl(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientOut registerClient(Client client) {
        return ClientMapper.toDomain(clientRepository.save(ClientMapper.toEntity(client)));
    }

    @Override
    public ClientOut getClient(String cpf) {
        return ClientMapper.toDomain(clientRepository.findByDocument(cpf));
    }

    @Override
    public ClientEntity getClientEntity(String cpf) {
        return clientRepository.findByDocument(cpf);
    }
}
