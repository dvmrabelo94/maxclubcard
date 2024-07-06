package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequest(
        Long luckyNumber,
        String cardNumber,
        BigDecimal value,
        LocalDateTime transactionDate) {
}