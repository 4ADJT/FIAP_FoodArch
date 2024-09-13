package br.com.fiap.foodarch.domain.usecases.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.User;

import java.util.List;

public class ListUsers {
  private final UserRepository repository;

  public ListUsers(
      UserRepository repository
  ) {
    this.repository = repository;
  }

  public List<User> execute() {
    return repository.listUsers();
  }
}
