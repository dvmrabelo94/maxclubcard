package br.com.diogenes.maxclubcard.dataprovider.transaction.repository;

import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
