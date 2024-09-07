package br.com.fiap.foodarch.infra.gateways;

import br.com.fiap.foodarch.application.gateways.UserRepository;
import br.com.fiap.foodarch.domain.entities.user.User;
import br.com.fiap.foodarch.infra.gateways.entities.UserEntity;
import br.com.fiap.foodarch.infra.gateways.exception.UserAlreadyExistsException;
import br.com.fiap.foodarch.infra.gateways.exception.UserNotExistsException;
import br.com.fiap.foodarch.infra.persistance.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public class JpaUserRepository implements UserRepository {
    private final IUserRepository repository;
    private final UserEntityMapper mapper;

    @Autowired
    public JpaUserRepository(
        IUserRepository repository,
        UserEntityMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = mapper.toEntity(user);

        List<User> alreadyExists = this.findByEmailOrCpf(userEntity.getEmail(), userEntity.getCpf());

        if(!alreadyExists.isEmpty()) {
            throw new UserAlreadyExistsException("User already exists.");
        }

        userEntity = repository.save(userEntity);

        user = mapper.toDomain(userEntity);

        return user;
    }

    @Override
    public List<User> listUsers() {
        List<UserEntity> users = repository.findAll();
        Stream<User> userStream = users.stream().map(mapper::toDomain);

        return userStream.toList();

    }

    @Override
    public List<User> findByEmailOrCpf(String email, String cpf) {
        List<UserEntity> users = repository.findByEmailOrCpf(email, cpf);

        Stream<User> userStream = users.stream().map(mapper::toDomain);

        return userStream.toList();

    }

    @Override
    public User updateUser(User user) {
        UserEntity users = repository.findByEmailOrCpf(user.getEmail(), user.getCpf()).get(0);

        if(users == null) {
            throw new UserNotExistsException("User not found.");
        }

        users.setBirthdate(user.getBirthdate());
        users.setName(user.getName());

        UserEntity userUpdated = repository.save(users);

        return mapper.toDomain(userUpdated);

    }

}
