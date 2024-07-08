package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.TransactionGateway;
import br.com.diogenes.maxclubcard.core.usecase.TransactionUseCase;
import br.com.diogenes.maxclubcard.entrypoint.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Service
public class TransactionUseCaseImpl implements TransactionUseCase {

    public static final String MAXCLUBCARD = "MAXCLUBCARD";
    private final TransactionGateway transactionGateway;
    private final CardGateway cardGateway;

    public TransactionUseCaseImpl(TransactionGateway transactionGateway, CardGateway cardGateway) {
        this.transactionGateway = transactionGateway;
        this.cardGateway = cardGateway;
    }

    @Override
    public TransactionOut registerTransaction(Transaction transaction) {
        log.info("Registering transaction: " + transaction.transactionNumber() + " " + transaction.transactionDate());
        var card = cardGateway.getCard(transaction.cardNumber());
        if (!isCardValid(card)) {
            log.error("Transaction with card is invalid: " + transaction.cardNumber());
            throw new BusinessException("Transaction with card is invalid");
        }
        return transactionGateway.registerTransaction(transaction);
    }

    private boolean isCardValid(CardOut card) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        try {
            YearMonth cardExpiration = YearMonth.parse(card.expirationDate(), formatter);
            YearMonth currentMonth = YearMonth.now();
            return cardExpiration.isBefore(currentMonth) && card.brandCard().equals(MAXCLUBCARD);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use MM/yy.");
        }
    }
}
