package br.com.diogenes.maxclubcard.core.card.usecase;

import br.com.diogenes.maxclubcard.core.card.domain.Card;
import br.com.diogenes.maxclubcard.core.card.gateway.CardGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardUseCase {

    private final CardGateway cardGateway;

    public CardUseCase(CardGateway cardGateway) {
        this.cardGateway = cardGateway;
    }

        public void registerCard(Card card) {
            log.info("Registering card: " + card.number());
            cardGateway.registerCard(card);
            log.info("Card registered: " + card.number());
        }
}
