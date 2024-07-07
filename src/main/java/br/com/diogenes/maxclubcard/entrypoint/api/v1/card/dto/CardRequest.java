package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto;

import java.time.LocalDate;

public record CardRequest(
        Long id,
        String cardNumber,
        LocalDate expirationDate,
        String typeCard,
        String brandCard,
        String document) {
}
