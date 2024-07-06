package br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto;

import java.time.LocalDate;

public record ClientRequest(
        String name,
        String document,
        LocalDate birth,
        String gender,
        String email,
        String phoneNumber) {
}
