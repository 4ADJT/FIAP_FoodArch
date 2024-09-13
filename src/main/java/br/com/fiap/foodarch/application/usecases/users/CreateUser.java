package br.com.fiap.foodarch.application.usecases.users;

import br.com.fiap.foodarch.application.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.entities.users.UserFactory;
import br.com.fiap.foodarch.domain.records.users.UserInput;

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
