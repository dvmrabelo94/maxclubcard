package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.gateway.BrandCardGateway;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.usecase.CardUseCase;
import br.com.diogenes.maxclubcard.core.utils.CardValidation;
import br.com.diogenes.maxclubcard.entrypoint.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardUseCaseImpl implements CardUseCase {

    private final CardGateway cardGateway;
    private final BrandCardGateway brandCardGateway;

    public CardUseCaseImpl(CardGateway cardGateway, BrandCardGateway brandCardGateway) {
        this.cardGateway = cardGateway;
        this.brandCardGateway = brandCardGateway;
    }

    @Override
    public CardOut registerCard(Card card) {
        log.info("Registering card: " + card.cardNumber());
        var brandList = brandCardGateway.findAllByIsActiveTrue();
        if(!CardValidation.isValidCard(card.expirationDate(), card.brandCard(), brandList)) {
            log.error("Card is invalid: " + card.cardNumber());
            throw new BusinessException("Card is invalid");
        }
        return cardGateway.registerCard(card);
    }


}
