package br.com.fiap.foodarch.application.usecases;

import br.com.fiap.foodarch.application.gateways.UserRepository;
import br.com.fiap.foodarch.domain.entities.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
