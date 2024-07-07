package br.com.diogenes.maxclubcard.dataprovider.client.repository;

import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

        ClientEntity findByDocument(String document);
}
