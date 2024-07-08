package br.com.diogenes.maxclubcard.core.domain.card;

public record Card(
        String cardNumber,
        String expirationDate,
        String typeCard,
        String brandCard,
        String document
) {
}
