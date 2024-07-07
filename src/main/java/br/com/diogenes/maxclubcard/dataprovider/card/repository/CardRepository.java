package br.com.diogenes.maxclubcard.dataprovider.card.repository;

import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<CardEntity, Long> {

    CardEntity findByCardNumber(String number);
}
