package br.com.diogenes.maxclubcard.dataprovider.card.mapper;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;

public class CardMapper {

    public static CardEntity toEntity(Card card, ClientEntity client) {
        return CardEntity.builder()
                .cardNumber(card.cardNumber())
                .brandCard(card.brandCard())
                .typeCard(card.typeCard())
                .expirationDate(card.expirationDate())
                .client(client)
                .build();
    }

    public static CardOut toDomain(CardEntity cardEntity) {
        return new CardOut(
                cardEntity.getId(),
                cardEntity.getCardNumber(),
                cardEntity.getExpirationDate(),
                cardEntity.getTypeCard(),
                cardEntity.getBrandCard(),
                cardEntity.getClient().getDocument()
        );
    }
}
