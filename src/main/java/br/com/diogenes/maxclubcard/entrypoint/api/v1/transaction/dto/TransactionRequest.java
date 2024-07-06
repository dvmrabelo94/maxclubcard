package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto;

import java.time.LocalDateTime;

public record TransactionRequest(
        Long id,
        Long luckyNumber,
        LocalDateTime transactionDate,
        Boolean isValid) {
}