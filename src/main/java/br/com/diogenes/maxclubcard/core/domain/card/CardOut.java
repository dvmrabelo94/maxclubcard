package br.com.diogenes.maxclubcard.core.domain.card;

import java.time.LocalDate;

public record CardOut(
        Long id,
        String number,
        String expirationDate,
        String typeCard,
        String brandCard,
        String document) {
}
