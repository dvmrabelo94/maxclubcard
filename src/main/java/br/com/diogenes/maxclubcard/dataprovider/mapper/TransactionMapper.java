package br.com.diogenes.maxclubcard.dataprovider.mapper;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;

import java.util.List;
import java.util.UUID;

public class TransactionMapper {

    public static TransactionEntity toEntity(Transaction transaction, CardEntity card) {
        return TransactionEntity.builder()
                .transactionNumber(transaction.transactionNumber())
                .card(card)
                .value(transaction.value())
                .transactionDate(transaction.transactionDate())
                .build();
    }

    public static TransactionOut toDomain(List<LuckyNumber> luckyNumberList) {
        return new TransactionOut(
                luckyNumberList
        );
    }
}
