package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.facade;

import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.usecase.CardUseCase;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardResponse;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.mapper.CardMapperEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardFacade {

    private final CardUseCase cardUseCase;

    public CardFacade(final CardUseCase cardUseCase) {
        this.cardUseCase = cardUseCase;
    }

    public CardResponse registerCard(CardRequest cardRequest) {
        var card = CardMapperEntryPoint.toCard(cardRequest);
        return CardMapperEntryPoint.toCardResponse(cardUseCase.registerCard(card));
    }
}
