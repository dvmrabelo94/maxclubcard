package br.com.diogenes.maxclubcard.dataprovider.luckynumber.repository;

import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface LuckyNumberRepository extends CrudRepository<LuckyNumberEntity, Long> {

    List<LuckyNumberEntity> findByIsValid(Boolean isValid);

    @Modifying
    @Query("update LuckyNumberEntity l set l.isValid = :isValid where l.luckyNumber in :luckyNumber")
    void updateAllByLuckyNumbers(@Param("isValid") Boolean isValid, @Param("luckyNumber") List<String> luckyNumber);
}
