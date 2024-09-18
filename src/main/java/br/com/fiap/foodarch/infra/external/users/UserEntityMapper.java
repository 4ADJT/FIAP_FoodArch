package br.com.fiap.foodarch.infra.external.users;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntityMapper;

import java.util.Set;
import java.util.stream.Collectors;

public class UserEntityMapper {

  public UserEntity toEntity(User user) {
    if (user == null) {
      return null;
    }

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
    if (userEntity == null) {
      return null;
    }

   Set<Restaurant> restaurants = userEntity.getRestaurants().stream().map(
       new RestaurantEntityMapper()::toDomain
   ).collect(Collectors.toSet());

    return new User(
        userEntity.getId(),
        userEntity.getName(),
        userEntity.getEmail(),
        userEntity.getBirthdate(),
        userEntity.getCpf(),
        restaurants,
        userEntity.getCreated_at(),
        userEntity.getUpdated_at()
    );
  }
}
