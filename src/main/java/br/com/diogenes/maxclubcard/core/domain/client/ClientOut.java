package br.com.diogenes.maxclubcard.core.domain.client;

import java.time.LocalDate;

public record ClientOut(
        String name,
        String email,
        LocalDate birth,
        String gender,
        String document) {
}
