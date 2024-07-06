package br.com.diogenes.maxclubcard.core.card.domain;

import java.time.LocalDate;

public record Card(
        String number,
        LocalDate expirationDate,
        String typeCard,
        String brandCard
) {
}
