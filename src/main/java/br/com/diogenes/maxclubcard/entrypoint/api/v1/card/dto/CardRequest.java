package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto;

import java.time.LocalDate;

public record CardRequest(
        Long id,
        String cardNumber,
        LocalDate expirationDate,
        String typeCard,
        String brandCard,
        String document) {

    public CardRequest {
        checkNotNull(cardNumber, "cardNumber");
        checkNotNull(expirationDate, "expirationDate");
        checkNotNull(typeCard, "typeCard");
        checkNotNull(brandCard, "brandCard");
        checkNotNull(document, "document");
    }

    private static <T> void checkNotNull(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " não pode ser nulo");
        }
    }
}
