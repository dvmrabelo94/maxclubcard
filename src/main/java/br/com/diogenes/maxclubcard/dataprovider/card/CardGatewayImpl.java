package br.com.diogenes.maxclubcard.dataprovider.card;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.card.repository.CardRepository;
import br.com.diogenes.maxclubcard.dataprovider.mapper.CardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardGatewayImpl implements CardGateway {

    private final CardRepository cardRepository;
    private final ClientGateway clientGateway;

    public CardGatewayImpl(final CardRepository cardRepository, final ClientGateway clientGateway) {
        this.cardRepository = cardRepository;
        this.clientGateway = clientGateway;
    }

    @Override
    public CardOut registerCard(Card card) {
        var client = clientGateway.getClientEntity(card.document());
        return CardMapper.toDomain(cardRepository.save(CardMapper.toEntity(card, client)));
    }

    @Override
    public CardEntity getCardEntity(String number) {
        return cardRepository.findByCardNumber(number);
    }

    @Override
    public CardOut getCard(String number) {
        return CardMapper.toDomain(cardRepository.findByCardNumber(number));
    }
}
