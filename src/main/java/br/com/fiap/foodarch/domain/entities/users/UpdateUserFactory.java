package br.com.fiap.foodarch.domain.entities.users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateUserFactory {
  public User updateUser(UUID id, String name, String email, LocalDate birthdate, String cpf, LocalDateTime createdAt) {
    return User.updateUser()
        .id(id)
        .name(name)
        .email(email)
        .birthdate(birthdate)
        .cpf(cpf)
        .createdAt(createdAt)
        .build();
  }
}
