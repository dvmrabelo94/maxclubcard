package br.com.diogenes.maxclubcard.dataprovider.transaction;

import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.LuckyNumberGateway;
import br.com.diogenes.maxclubcard.core.gateway.TransactionGateway;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.mapper.LuckyNumberMapper;
import br.com.diogenes.maxclubcard.dataprovider.transaction.mapper.TransactionMapper;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionGatewayImpl implements TransactionGateway {

    public static final String EXCEPTION_MESSAGE = "M=registerTransaction, message=Error registering transaction, exceptionMessage={}";
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
    @Transactional
    public TransactionOut registerTransaction(Transaction transaction) {
        log.info("M=registerTransaction, message=Registering transaction");
        var card = cardGateway.getCardEntity(transaction.cardNumber());
        if (card == null) {
            log.error("M=registerTransaction, message=Error registering transaction, exceptionMessage=Card not found");
            throw new EntityNotFoundException("Card not found");
        }
        try {
            var transactionEntity = TransactionMapper.toEntity(transaction, card);
            transactionRepository.save(transactionEntity);
            var luckyNumberList = luckyNumberGateway.insertAll(generateLuckyNumberList(transactionEntity), TransactionMapper.toEntity(transaction, card));
            log.info("M=registerTransaction, message=Transaction registered with success, transactionEntity={}", transactionEntity);
            return TransactionMapper.toDomain(luckyNumberList);
        } catch (DataIntegrityViolationException e) {
            log.error(EXCEPTION_MESSAGE, e.getMessage());
            throw new DataIntegrityViolationException(EXCEPTION_MESSAGE + " Error saving transaction: Duplicate entry found.");
        }

    }

    private List<LuckyNumberEntity> generateLuckyNumberList(TransactionEntity transaction) {
        log.info("M=generateLuckyNumberList, message=Generating lucky number list, transaction={}", transaction);
        int numberOfLuckyNumbers = transaction.getValue().divideToIntegralValue(BigDecimal.valueOf(150)).intValue();
        List<LuckyNumberEntity> luckyNumbers = new ArrayList<>();

        for (int i = 0; i < numberOfLuckyNumbers; i++) {
            luckyNumbers.add(LuckyNumberMapper.toEntity(transaction));
        }
        log.info("M=generateLuckyNumberList, message=Lucky number list generated with success, luckyNumbers={}", luckyNumbers);
        return luckyNumbers;
    }
}
