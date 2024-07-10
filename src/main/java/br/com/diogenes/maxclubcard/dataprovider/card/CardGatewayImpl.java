package br.com.diogenes.maxclubcard.dataprovider.card;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.card.repository.CardRepository;
import br.com.diogenes.maxclubcard.dataprovider.card.mapper.CardMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
        if (client == null) {
            log.error("M=registerCard, message=Error registering card, exceptionMessage=Client not found");
            throw new EntityNotFoundException("Client not found");
        }
        try {
            var cardReturn = CardMapper.toDomain(cardRepository.save(CardMapper.toEntity(card, client)));
            log.info("M=registerCard, Card registered: " + cardReturn);
            return cardReturn;
        } catch (DataIntegrityViolationException e) {
            log.error(EXCEPTION_MESSAGE, e.getMessage());
            throw new DataIntegrityViolationException(EXCEPTION_MESSAGE + " Error saving card: Duplicate entry found.");
        }

    }

    @Override
    public CardEntity getCardEntity(String number) {
        log.info("M=getCardEntity, Start search card by number: " + number);
        var card = cardRepository.findByCardNumber(number);
        if (card == null) {
            log.error("M=getCardEntity, message=Error searching card, exceptionMessage=Card not found");
            throw new EntityNotFoundException("Card not found");
        }
        log.info("M=getCardEntity, Card found: " + card);
        return card;
    }

    @Override
    public CardOut getCard(String number) {
        log.info("M=getCard, Start search card by number: " + number);
        var cardEntity = cardRepository.findByCardNumber(number);
        if (cardEntity == null) {
            log.error("M=getCard, message=Error searching card, exceptionMessage=Card not found");
            throw new EntityNotFoundException("Card not found");
        }
        var card = CardMapper.toDomain(cardEntity);
        log.info("M=getCard, Card found: " + card);
        return card;
    }
}
