package br.com.diogenes.maxclubcard.core.transaction.domain;

import java.time.LocalDateTime;

public record Transanction(
        Long luckyNumber,
        LocalDateTime transactionDate
) {
}
