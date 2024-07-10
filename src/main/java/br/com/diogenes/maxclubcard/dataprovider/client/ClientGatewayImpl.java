package br.com.diogenes.maxclubcard.dataprovider.client;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.client.ClientOut;
import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.repository.ClientRepository;
import br.com.diogenes.maxclubcard.dataprovider.client.mapper.ClientMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            log.info("M=registerClient, Start register client: {}", client);
            var clientSaved = ClientMapper.toDomain(clientRepository.save(ClientMapper.toEntity(client)));
            log.info("M=registerClient, Client registered: {}", clientSaved);
            return clientSaved;
        } catch (DataIntegrityViolationException e) {
            log.error("M=registerClient, message=Error registering client, exceptionMessage={}", e.getMessage());
            throw new DataIntegrityViolationException("Error registering client: Duplicate entry found.");
        }
    }

    @Override
    public ClientOut getClient(String cpf) {
        log.info("M=getClient, Start search client by cpf: {}", cpf);
        var clientEntity = clientRepository.findByDocument(cpf);
        if (clientEntity == null) {
            log.error("M=getClient, message=Error searching client, exceptionMessage=Client not found");
            throw new EntityNotFoundException("Client not found");
        }
        var client =  ClientMapper.toDomain(clientEntity);
        log.info("M=getClient, Client found: {}", client);
        return client;
    }

    @Override
    public ClientEntity getClientEntity(String cpf) {
        log.info("M=getClientEntity, Start search client by cpf: {}", cpf);
        var client = clientRepository.findByDocument(cpf);
        if (client == null) {
            log.error("M=getClientEntity, message=Error searching client, exceptionMessage=Client not found");
            throw new EntityNotFoundException("Client not found");
        }
        log.info("M=getClientEntity, Client found: {}", client);
        return client;
    }
}
