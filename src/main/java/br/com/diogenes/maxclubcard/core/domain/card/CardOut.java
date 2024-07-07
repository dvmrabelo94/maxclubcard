package br.com.diogenes.maxclubcard.core.domain.card;

import java.time.LocalDate;

public record CardOut(
        String number,
        LocalDate expirationDate,
        String typeCard,
        String brandCard,
        String document) {
}
