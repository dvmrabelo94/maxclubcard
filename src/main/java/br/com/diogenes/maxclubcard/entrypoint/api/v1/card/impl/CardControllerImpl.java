package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.impl;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.CardController;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardControllerImpl implements CardController {

    @Override
    public ResponseEntity<CardResponse> registerCard(CardRequest cardRequest) {
        return ResponseEntity.ok(new CardResponse());
    }
}
