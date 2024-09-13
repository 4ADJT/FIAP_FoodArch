package br.com.fiap.foodarch.domain.records.users;

import jakarta.validation.constraints.*;

import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserInput(

    @NotNull(message = "Name should not be null")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 100, message = "Name should be between 2 and 100 characters")
    String name,

    @NotNull(message = "Email should not be null")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    String email,

    @NotNull(message = "Birthdate should not be null")
    @PastOrPresent(message = "Birthdate should be in the past or present.")
    LocalDate birthdate,

    @NotNull(message = "CPF should not be null")
    @NotEmpty(message = "CPF should not be empty")
    @CPF(message = "CPF should be valid.")
    String cpf

) {
}
