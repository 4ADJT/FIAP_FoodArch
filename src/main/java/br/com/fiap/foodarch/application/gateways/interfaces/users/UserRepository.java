package br.com.fiap.foodarch.application.gateways.interfaces.users;

import br.com.fiap.foodarch.domain.entities.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

  User createUser(User user);

  Page<User> listUsers(Pageable pageable);

  List<User> findByEmailOrCpf(String email, String cpf);

  User updateUser(User user);

  User findById(UUID id);

}
