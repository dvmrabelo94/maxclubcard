package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.usecase.CardUseCase;
import br.com.diogenes.maxclubcard.entrypoint.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class CardUseCaseImpl implements CardUseCase {

    public static final String MAXCLUBCARD = "MAXCLUBCARD";
    private final CardGateway cardGateway;

    public CardUseCaseImpl(CardGateway cardGateway) {
        this.cardGateway = cardGateway;
    }

    @Override
    public CardOut registerCard(Card card) {
        log.info("Registering card: " + card.cardNumber());
        if(!isCardValid(card)) {
            log.error("Card is invalid: " + card.cardNumber());
            throw new BusinessException("Card is invalid");
        }
        return cardGateway.registerCard(card);
    }

    private boolean isCardValid(Card card) {
        return card != null &&
                (card.expirationDate() != null && card.expirationDate().isAfter(LocalDate.now())) &&
                (card.brandCard() != null && card.brandCard().equalsIgnoreCase(MAXCLUBCARD));
    }
}
