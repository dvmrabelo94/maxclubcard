package br.com.diogenes.maxclubcard.dataprovider.client;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.client.ClientOut;
import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.repository.ClientRepository;
import br.com.diogenes.maxclubcard.dataprovider.client.mapper.ClientMapper;
import jakarta.transaction.Transactional;
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
    @Transactional
    public ClientOut registerClient(Client client) {
        log.info("M=registerClient, Start register client: {}", client);
        var clientSaved = ClientMapper.toDomain(clientRepository.save(ClientMapper.toEntity(client)));
        log.info("M=registerClient, Client registered: {}", clientSaved);
        return clientSaved;
    }

    @Override
    public ClientOut getClient(String cpf) {
        log.info("M=getClient, Start search client by cpf: {}", cpf);
        var client =  ClientMapper.toDomain(clientRepository.findByDocument(cpf));
        log.info("M=getClient, Client found: {}", client);
        return client;
    }

    @Override
    public ClientEntity getClientEntity(String cpf) {
        log.info("M=getClientEntity, Start search client by cpf: {}", cpf);
        var client = clientRepository.findByDocument(cpf);
        log.info("M=getClientEntity, Client found: {}", client);
        return client;
    }
}
