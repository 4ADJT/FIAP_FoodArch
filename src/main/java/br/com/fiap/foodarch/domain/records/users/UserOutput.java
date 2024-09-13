package br.com.fiap.foodarch.domain.records.users;

import java.time.LocalDate;
import java.util.UUID;

public record UserOutput(
    UUID id,
    String name,
    String email,
    LocalDate birthdate,
    String cpf
) {
}
