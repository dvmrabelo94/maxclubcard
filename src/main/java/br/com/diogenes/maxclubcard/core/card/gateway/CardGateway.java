package br.com.diogenes.maxclubcard.core.card.gateway;

import br.com.diogenes.maxclubcard.core.card.domain.Card;

public interface CardGateway {

    void registerCard(Card card);
}
