package br.com.fiap.foodarch.application.presenters.users;

import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserOutput;

public class UserPresenter {
  public static UserOutput userResponse(User user) {
    return new UserOutput(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getBirthdate(),
        user.getCpf()
      );
  }

}
