package br.com.diogenes.maxclubcard.dataprovider.transaction.repository;

import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
