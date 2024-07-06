package br.com.diogenes.maxclubcard.core.usecase;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.transaction.Transanction;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.TransactionGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class TransactionUseCase {

    public static final String MAXCLUBCARD = "MAXCLUBCARD";
    private final TransactionGateway transactionGateway;
    private final CardGateway cardGateway;

    public TransactionUseCase(TransactionGateway transactionGateway, CardGateway cardGateway) {
        this.transactionGateway = transactionGateway;
        this.cardGateway = cardGateway;
    }

    public void registerTransaction(Transanction transaction) {
        log.info("Registering transaction: " + transaction.luckyNumber() + " " + transaction.transactionDate());
        var card = cardGateway.getCard(transaction.cardNumber());
        if (!isCardValid(card)) {
            log.error("Transaction with card is invalid: " + transaction.cardNumber());
            return;
        }
        transactionGateway.registerTransaction(transaction);
        log.info("Transaction registered: " + transaction.luckyNumber());
    }

    private boolean isCardValid(Card card) {
        return card != null &&
                (card.expirationDate() != null && card.expirationDate().isAfter(LocalDate.now())) &&
                (card.brandCard() != null && card.brandCard().equalsIgnoreCase(MAXCLUBCARD));
    }
}
