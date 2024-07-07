package br.com.diogenes.maxclubcard.core.usecase;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;

public interface CardUseCase {

    CardOut registerCard(Card card);
}
