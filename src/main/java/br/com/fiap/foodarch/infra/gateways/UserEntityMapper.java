package br.com.fiap.foodarch.infra.gateways;

import br.com.fiap.foodarch.domain.entities.user.User;
import br.com.fiap.foodarch.infra.gateways.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

  public UserEntity toEntity(User user) {
    return new UserEntity(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getBirthdate(),
        user.getCpf(),
        user.getCreatedAt(),
        user.getUpdatedAt()
    );
  }

  public User toDomain(UserEntity userEntity) {
    return new User(
        userEntity.getId(),
        userEntity.getName(),
        userEntity.getEmail(),
        userEntity.getBirthdate(),
        userEntity.getCpf(),
        userEntity.getCreated_at(),
        userEntity.getUpdated_at()
    );
  }
}
