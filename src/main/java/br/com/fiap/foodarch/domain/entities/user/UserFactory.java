package br.com.fiap.foodarch.domain.entities.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
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
