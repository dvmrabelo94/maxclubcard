package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;
import br.com.diogenes.maxclubcard.core.gateway.BrandCardGateway;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.TransactionGateway;
import br.com.diogenes.maxclubcard.core.usecase.TransactionUseCase;
import br.com.diogenes.maxclubcard.core.utils.CardValidation;
import br.com.diogenes.maxclubcard.entrypoint.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionUseCaseImpl implements TransactionUseCase {

    private final TransactionGateway transactionGateway;
    private final CardGateway cardGateway;
    private final BrandCardGateway brandCardGateway;

    public TransactionUseCaseImpl(
            TransactionGateway transactionGateway,
            CardGateway cardGateway,
            BrandCardGateway brandCardGateway) {
        this.transactionGateway = transactionGateway;
        this.cardGateway = cardGateway;
        this.brandCardGateway = brandCardGateway;
    }

    @Override
    public TransactionOut registerTransaction(Transaction transaction) {
        log.info("Registering transaction: " + transaction.transactionNumber() + " " + transaction.transactionDate());
        var card = cardGateway.getCard(transaction.cardNumber());
        var brandList = brandCardGateway.findAllByIsActiveTrue();
        if (!CardValidation.isValidCard(card.expirationDate(), card.brandCard(), brandList)) {
            log.error("Transaction with card is invalid: " + transaction.cardNumber());
            throw new BusinessException("Transaction with card is invalid");
        }
        return transactionGateway.registerTransaction(transaction);
    }


}
