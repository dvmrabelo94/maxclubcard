package br.com.diogenes.maxclubcard.core.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transanction(
        Long luckyNumber,
        String cardNumber,
        BigDecimal value,
        LocalDateTime transactionDate
) {
}
