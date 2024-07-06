package br.com.diogenes.maxclubcard.core.domain.card;

import java.time.LocalDate;

public record Card(
        String cardNumber,
        LocalDate expirationDate,
        String typeCard,
        String brandCard
) {
}
