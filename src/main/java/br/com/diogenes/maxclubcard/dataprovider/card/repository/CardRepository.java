package br.com.diogenes.maxclubcard.dataprovider.card.repository;

import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CardRepository extends CrudRepository<CardEntity, Long> {

    CardEntity findByCardNumber(String cardNumber);
}
