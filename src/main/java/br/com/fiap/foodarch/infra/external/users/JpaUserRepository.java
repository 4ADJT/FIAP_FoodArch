package br.com.fiap.foodarch.infra.external.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.UserAlreadyExistsException;
import br.com.fiap.foodarch.domain.exceptions.UserNotExistsException;
import br.com.fiap.foodarch.infra.gateways.persistance.users.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
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
            throw new UserAlreadyExistsException("User already exists.", HttpStatus.BAD_REQUEST);
        }

        userEntity = repository.save(userEntity);

        user = mapper.toDomain(userEntity);

        return user;
    }

    @Override
    public Page<User> listUsers(Pageable pageable) {
        Page<UserEntity> users = this.repository.findAll(pageable);

        return users.map(mapper::toDomain);

    }

    @Override
    public List<User> findByEmailOrCpf(String email, String cpf) {

        List<UserEntity> users = repository.findByEmailOrCpf(email, cpf);

        Stream<User> userStream = users.stream().map(mapper::toDomain);

        return userStream.toList();

    }

    @Override
    public User updateUser(User user) {

        user.setBirthdate(user.getBirthdate());
        user.setName(user.getName());

        UserEntity userEntity = mapper.toEntity(user);

        UserEntity userUpdated = repository.save(userEntity);

        return mapper.toDomain(userUpdated);
    }

    @Override
    public User findById(UUID id) {
        UserEntity user = this.repository.findById(id).orElseThrow(
            () -> new UserNotExistsException("User not found.", HttpStatus.BAD_REQUEST)
        );

        return mapper.toDomain(user);
    }

}
