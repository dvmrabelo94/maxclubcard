package br.com.diogenes.maxclubcard.dataprovider.card;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.card.repository.CardRepository;
import br.com.diogenes.maxclubcard.dataprovider.card.mapper.CardMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardGatewayImpl implements CardGateway {

    public static final String EXCEPTION_MESSAGE = "M=registerCard, message=Error registering card, exceptionMessage={}";
    private final CardRepository cardRepository;
    private final ClientGateway clientGateway;

    public CardGatewayImpl(final CardRepository cardRepository, final ClientGateway clientGateway) {
        this.cardRepository = cardRepository;
        this.clientGateway = clientGateway;
    }

    @Override
    @Transactional
    public CardOut registerCard(Card card) {
        log.info("M=registerCard, Start register card: " + card);
        var client = clientGateway.getClientEntity(card.document());
        var cardReturn = CardMapper.toDomain(cardRepository.save(CardMapper.toEntity(card, client)));
        log.info("M=registerCard, Card registered: " + cardReturn);
        return cardReturn;
    }

    @Override
    public CardEntity getCardEntity(String number) {
        log.info("M=getCardEntity, Start search card by number: " + number);
        var card = cardRepository.findByCardNumber(number);
        log.info("M=getCardEntity, Card found: " + card);
        return card;
    }

    @Override
    public CardOut getCard(String number) {
        log.info("M=getCard, Start search card by number: " + number);
        var card = CardMapper.toDomain(cardRepository.findByCardNumber(number));
        log.info("M=getCard, Card found: " + card);
        return card;
    }
}
