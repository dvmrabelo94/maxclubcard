package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto;

public record CardRequest(
        String cardNumber,
        String expirationDate,
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
