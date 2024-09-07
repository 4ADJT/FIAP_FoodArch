package br.com.fiap.foodarch.application.gateways;

import br.com.fiap.foodarch.domain.entities.user.User;

import java.util.List;

public interface UserRepository {

  User createUser(User user);

  List<User> listUsers();

  List<User> findByEmailOrCpf(String email, String cpf);

  User updateUser(User user);
}
