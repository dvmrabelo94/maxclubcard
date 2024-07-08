package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequest(
        String transactionNumber,
        String cardNumber,
        BigDecimal value,
        LocalDateTime transactionDate) {

    public TransactionRequest {
        checkNotNull(transactionNumber, "transactionNumber");
        checkNotNull(cardNumber, "cardNumber");
        checkNotNull(value, "value");
        checkNotNull(transactionDate, "transactionDate");

    }

    private static <T> void checkNotNull(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " não pode ser nulo");
        }
    }
}