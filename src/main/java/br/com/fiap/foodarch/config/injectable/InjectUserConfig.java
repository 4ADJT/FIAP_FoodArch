package br.com.fiap.foodarch.config.injectable;

import br.com.fiap.foodarch.application.gateways.UserRepository;
import br.com.fiap.foodarch.application.usecases.CreateUser;
import br.com.fiap.foodarch.application.usecases.ListUsers;
import br.com.fiap.foodarch.application.usecases.UpdateUser;
import br.com.fiap.foodarch.domain.entities.user.UserFactory;
import br.com.fiap.foodarch.infra.controller.UserController;
import br.com.fiap.foodarch.infra.gateways.JpaUserRepository;
import br.com.fiap.foodarch.infra.gateways.UserEntityMapper;
import br.com.fiap.foodarch.infra.persistance.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectUserConfig {

  @Bean
  public UserFactory userFactory() {
      return new UserFactory();
  }

  @Bean
  public UserEntityMapper userEntityMapper() {
      return new UserEntityMapper();
  }

  @Bean
  UpdateUser updateUser(UserRepository repository, UserFactory factory) {
    return new UpdateUser(repository, factory);
  }

  @Bean
  ListUsers listUser(UserRepository repository) {
    return new ListUsers(repository);
  }

  @Bean
  CreateUser createUser(UserRepository repository, UserFactory factory) {
    return new CreateUser(repository, factory);
  }

  @Bean
  JpaUserRepository jpaUserRepository(IUserRepository repository, UserEntityMapper mapper) {
    return new JpaUserRepository(repository, mapper);
  }

  @Bean
  UserController userController(CreateUser createUser, UpdateUser updateUser, ListUsers listUsers) {
    return new UserController(createUser, updateUser, listUsers);
  }

}
