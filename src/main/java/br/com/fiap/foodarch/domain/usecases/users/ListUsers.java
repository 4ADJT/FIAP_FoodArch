package br.com.fiap.foodarch.domain.usecases.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class ListUsers {
  private final UserRepository repository;

  public ListUsers(
      UserRepository repository
  ) {
    this.repository = repository;
  }

  @Transactional
  public Page<User> execute(Pageable pageable) {
    return repository.listUsers(pageable);
  }
}
