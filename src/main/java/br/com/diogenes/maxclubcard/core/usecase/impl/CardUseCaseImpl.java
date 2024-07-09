package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.usecase.CardUseCase;
import br.com.diogenes.maxclubcard.core.utils.CardValidation;
import br.com.diogenes.maxclubcard.entrypoint.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        if(!CardValidation.isCardValid(card.expirationDate(), card.brandCard())) {
            log.error("Card is invalid: " + card.cardNumber());
            throw new BusinessException("Card is invalid");
        }
        return cardGateway.registerCard(card);
    }


}
