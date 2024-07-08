package br.com.diogenes.maxclubcard.core.gateway;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;

import java.util.List;

public interface LuckyNumberGateway {

    List<LuckyNumber> insertAll(List<LuckyNumberEntity> luckyNumberList, TransactionEntity transactionEntity);

    List<LuckyNumber> findByIsValid();

    void updateAllByLuckyNumbers(Boolean isValid, List<String> luckyNumber);
}
