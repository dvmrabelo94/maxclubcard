package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.mapper;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardResponse;

public class CardMapperEntryPoint {

    public static Card toCard(CardRequest cardRequest) {
        return new Card(
                cardRequest.cardNumber(),
                cardRequest.expirationDate(),
                cardRequest.typeCard(),
                cardRequest.brandCard(),
                cardRequest.document()
                );
    }

    public static CardResponse toCardResponse(CardOut cardOut) {
        return new CardResponse(
                cardOut.id(),
                cardOut.number(),
                cardOut.expirationDate(),
                cardOut.typeCard(),
                cardOut.brandCard(),
                cardOut.document()
        );
    }
}
