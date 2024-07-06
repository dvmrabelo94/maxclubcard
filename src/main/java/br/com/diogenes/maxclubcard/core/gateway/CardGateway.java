package br.com.diogenes.maxclubcard.core.gateway;

import br.com.diogenes.maxclubcard.core.domain.card.Card;

public interface CardGateway {

    void registerCard(Card card);

    Card getCard(String number);
}
