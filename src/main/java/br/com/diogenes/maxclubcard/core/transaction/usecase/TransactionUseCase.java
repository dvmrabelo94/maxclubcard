package br.com.diogenes.maxclubcard.core.transaction.usecase;

import br.com.diogenes.maxclubcard.core.transaction.domain.Transanction;
import br.com.diogenes.maxclubcard.core.transaction.gateway.TransactionGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionUseCase {

    private final TransactionGateway transactionGateway;

    public TransactionUseCase(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    public void registerTransaction(Transanction transaction) {
        log.info("Registering transaction: " + transaction.luckyNumber() + " " + transaction.transactionDate());
        transactionGateway.registerTransaction(transaction);
        log.info("Transaction registered: " + transaction.luckyNumber());
    }
}
