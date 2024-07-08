package br.com.diogenes.maxclubcard.dataprovider.luckynumber.mapper;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;

import java.util.UUID;

public class LuckyNumberMapper {

    public static LuckyNumberEntity toEntity(TransactionEntity transactionEntity) {
        return  LuckyNumberEntity.builder()
                .luckyNumber(UUID.randomUUID().toString())
                .isValid(true)
                .transaction(transactionEntity)
                .build();
    }

    public static LuckyNumber toDomain(LuckyNumberEntity luckyNumberEntity) {
        return new LuckyNumber(
                luckyNumberEntity.getLuckyNumber(),
                luckyNumberEntity.isValid()
        );
    }
}
