package br.com.fiap.foodarch.domain.entities.users;

import java.time.LocalDate;

public class CreateUserFactory {

  public User createUser(String name, String email, LocalDate birthdate, String cpf) {
    return User.createUser()
        .name(name)
        .email(email)
        .birthdate(birthdate)
        .cpf(cpf)
        .build();
  }

}
