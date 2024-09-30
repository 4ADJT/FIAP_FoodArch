package br.com.fiap.foodarch.domain.usecases.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.UpdateUserFactory;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.users.UserInput;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

public class UpdateUser {

    private final UserRepository repository;
    private final UpdateUserFactory factory;

    public UpdateUser(UserRepository repository, UpdateUserFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public User execute(UUID id, UserInput userInput) {

        User userToUpdate = this.repository.findById(id);

        List<User> userData = this.repository.findByEmailOrCpf(userInput.email(), userInput.cpf());

        if (userData.isEmpty() || userToUpdate == null) {
            throw new UserUnauthorizedException("User Unauthorized.", HttpStatus.UNAUTHORIZED);
        }

        if (userToUpdate.getId() != userData.get(0).getId()) {
            throw new UserUnauthorizedException("User Unauthorized.", HttpStatus.UNAUTHORIZED);
        }

        User user = factory.updateUser(userToUpdate.getId(), userInput.name(), userToUpdate.getEmail(), userInput.birthdate(), userToUpdate.getCpf(), userToUpdate.getCreatedAt());

        return repository.updateUser(user);
    }

}
