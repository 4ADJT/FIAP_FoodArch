package br.com.fiap.foodarch.application.gateways.interfaces.users;

import br.com.fiap.foodarch.domain.entities.users.User;

import java.util.List;

public interface UserRepository {

  User createUser(User user);

  List<User> listUsers();

  List<User> findByEmailOrCpf(String email, String cpf);

  User updateUser(User user);
}
