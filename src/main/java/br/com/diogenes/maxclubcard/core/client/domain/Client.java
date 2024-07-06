package br.com.diogenes.maxclubcard.core.client.domain;

import java.time.LocalDate;

public record Client(
        String name,
        String document,
        LocalDate birth,
        String gender,
        String email,
        String phoneNumber
) {
}
