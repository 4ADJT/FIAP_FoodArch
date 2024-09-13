package br.com.fiap.foodarch.domain.entities.users;

import java.time.LocalDate;

public class UserFactory {

  public User createUser(String name, String email, LocalDate birthdate, String cpf) {
    return new User.UserBuilder()
        .name(name)
        .email(email)
        .birthdate(birthdate)
        .cpf(cpf)
        .build();
  }

}
