package br.com.diogenes.maxclubcard.core.gateway;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;

public interface CardGateway {

    CardOut registerCard(Card card);

    CardEntity getCardEntity(String number);

    CardOut getCard(String number);
}
