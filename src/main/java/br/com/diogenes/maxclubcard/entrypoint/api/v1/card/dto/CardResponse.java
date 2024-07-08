package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto;

public record CardResponse(
        Long id,
        String cardNumber,
        String expirationDate,
        String typeCard,
        String brandCard,
        String document
) {
}
