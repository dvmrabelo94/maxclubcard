package br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto;

import java.time.LocalDate;

public record ClientRequest(
        String name,
        String document,
        LocalDate birth,
        String gender,
        String email,
        String phoneNumber) {

    public ClientRequest {
        checkNotNull(name, "name");
        checkNotNull(document, "document");
        checkNotNull(birth, "birth");
        checkNotNull(gender, "gender");
        checkNotNull(email, "email");
        checkNotNull(phoneNumber, "phoneNumber");
    }

    private static <T> void checkNotNull(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " não pode ser nulo");
        }
    }
}

