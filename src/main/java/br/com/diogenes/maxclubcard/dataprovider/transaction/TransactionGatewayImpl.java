package br.com.diogenes.maxclubcard.dataprovider.transaction;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.LuckyNumberGateway;
import br.com.diogenes.maxclubcard.core.gateway.TransactionGateway;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.mapper.LuckyNumberMapper;
import br.com.diogenes.maxclubcard.dataprovider.mapper.TransactionMapper;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionGatewayImpl implements TransactionGateway {

    private final TransactionRepository transactionRepository;
    private final CardGateway cardGateway;
    private final LuckyNumberGateway luckyNumberGateway;

    public TransactionGatewayImpl(
            final TransactionRepository transactionRepository,
            final CardGateway cardGateway,
            final LuckyNumberGateway luckyNumberGateway) {
        this.transactionRepository = transactionRepository;
        this.cardGateway = cardGateway;
        this.luckyNumberGateway = luckyNumberGateway;
    }

    @Override
    public TransactionOut registerTransaction(Transaction transaction) {
        var card = cardGateway.getCardEntity(transaction.cardNumber());
        var transactionEntity = TransactionMapper.toEntity(transaction, card);
        transactionRepository.save(transactionEntity);
        var luckyNumberList = luckyNumberGateway.insertAll(generateLuckyNumberList(transactionEntity), TransactionMapper.toEntity(transaction, card));
        return TransactionMapper.toDomain(luckyNumberList);
    }

    private List<LuckyNumberEntity> generateLuckyNumberList(TransactionEntity transaction) {
        int numberOfLuckyNumbers = transaction.getValue().remainder(BigDecimal.valueOf(150)).intValue();
        List<LuckyNumberEntity> luckyNumbers = new ArrayList<>();

        for (int i = 0; i < numberOfLuckyNumbers; i++) {
            luckyNumbers.add(LuckyNumberMapper.toEntity(transaction));
        }

        return luckyNumbers;
    }
}
