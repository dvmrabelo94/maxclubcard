package br.com.diogenes.maxclubcard.dataprovider.transaction.mapper;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;

import java.util.List;

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
