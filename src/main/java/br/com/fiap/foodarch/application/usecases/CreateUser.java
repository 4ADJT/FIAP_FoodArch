package br.com.fiap.foodarch.application.usecases;

import br.com.fiap.foodarch.application.gateways.UserRepository;
import br.com.fiap.foodarch.domain.entities.user.User;
import br.com.fiap.foodarch.domain.entities.user.UserFactory;
import br.com.fiap.foodarch.infra.controller.dtos.UserDTO;

public class CreateUser {
    private final UserRepository repository;
    private final UserFactory factory;

    public CreateUser(
        UserRepository repository,
        UserFactory factory
    ) {
        this.repository = repository;
        this.factory = factory;
    }

    public User execute(UserDTO userDTO)  {
        User user = factory.createUser(
            userDTO.name(),
            userDTO.email(),
            userDTO.birthdate(),
            userDTO.cpf()
        );

       return repository.createUser(user);
    }
}
