package br.com.diogenes.maxclubcard.dataprovider.client.repository;

import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

        ClientEntity findByDocument(String document);
}
