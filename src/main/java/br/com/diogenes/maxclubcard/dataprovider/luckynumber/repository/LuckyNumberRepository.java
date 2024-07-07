package br.com.diogenes.maxclubcard.dataprovider.luckynumber.repository;

import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuckyNumberRepository extends CrudRepository<LuckyNumberEntity, Long> {
}
