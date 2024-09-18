package br.com.fiap.foodarch.domain.usecases.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.User;

import java.util.UUID;

public class GetUserById {
  private final UserRepository repository;

  public GetUserById(
      UserRepository repository
  ) {
    this.repository = repository;
  }

  public User execute(UUID id) {
    return repository.findById(id);
  }
}
