package br.com.fiap.foodarch.domain.records.users;

import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Schema(name = "UserOutput", description = "User's output data")
public record UserOutput(

    @Schema(description = "User's id", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,

    @Schema(description = "User's name", example = "John Doe")
        String name,

    @Schema(description = "User's email", example = "test@test.com")
        String email,

    @Schema(description = "User's birthdate", example = "1990-01-01")
        LocalDate birthdate,

    @Schema(description = "User's cpf", example = "123.456.789-00")
        String cpf,

        Set<RestaurantOutput> restaurants
    ) {
}
