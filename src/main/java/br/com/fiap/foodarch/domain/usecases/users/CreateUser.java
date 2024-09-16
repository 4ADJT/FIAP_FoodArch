package br.com.fiap.foodarch.domain.usecases.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.entities.users.CreateUserFactory;
import br.com.fiap.foodarch.domain.records.users.UserInput;

public class CreateUser {
    private final UserRepository repository;
    private final CreateUserFactory factory;

    public CreateUser(
        UserRepository repository,
        CreateUserFactory factory
    ) {
        this.repository = repository;
        this.factory = factory;
    }

    public User execute(UserInput userInput)  {
        User user = factory.createUser(
            userInput.name(),
            userInput.email(),
            userInput.birthdate(),
            userInput.cpf()
        );

       return repository.createUser(user);
    }
}
