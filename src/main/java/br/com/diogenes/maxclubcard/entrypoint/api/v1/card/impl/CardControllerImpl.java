package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.impl;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.CardController;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardResponse;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.facade.CardFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardControllerImpl implements CardController {

    private final CardFacade cardFacade;

    public CardControllerImpl(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    @Override
    public ResponseEntity<CardResponse> registerCard(CardRequest cardRequest) {
        return ResponseEntity.ok(cardFacade.registerCard(cardRequest));
    }
}
