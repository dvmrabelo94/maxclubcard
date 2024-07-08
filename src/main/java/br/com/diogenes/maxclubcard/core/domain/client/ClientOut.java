package br.com.diogenes.maxclubcard.core.domain.client;

import java.time.LocalDate;

public record ClientOut(
        Long id,
        String name,
        String email,
        LocalDate birth,
        String gender,
        String document,
        String phoneNumber) {
}
