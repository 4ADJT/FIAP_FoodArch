package br.com.fiap.foodarch.infra.external.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.infra.external.users.UserEntity;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.users.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaRestaurantRepository implements RestaurantRepository {
  private final IRestaurantRepository repository;
  private final RestaurantEntityMapper mapper;
  private final IUserRepository userRepository;

  @Autowired
  public JpaRestaurantRepository(
      IRestaurantRepository repository,
      IUserRepository userRepository,
      RestaurantEntityMapper mapper
  ) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  @Override
  public Restaurant createRestaurant(Restaurant restaurant, UUID userId) {

    UserEntity userEntity = this.userRepository.getReferenceById(userId);

    RestaurantEntity restaurantEntity = mapper.toEntity(restaurant, userEntity);

    RestaurantEntity toSave = repository.save(restaurantEntity);

    return mapper.toDomain(toSave);
  }

  @Override
  public Page<Restaurant> listRestaurants(Pageable pageable) {
    Page<RestaurantEntity> restaurants = this.repository.findAll(pageable);

    return restaurants.map(mapper::toDomain);
  }

  @Override
  public Page<Restaurant> findByOwnerId(UUID ownerId, Pageable pageable) {
    Page<RestaurantEntity> restaurants = this.repository.findByOwnerId(ownerId, pageable);

    return restaurants.map(mapper::toDomain);
  }

  @Override
  public Restaurant updateRestaurant(Restaurant restaurant, UUID userId) {
    UserEntity userEntity = this.userRepository.getReferenceById(userId);

    if(userEntity.getRestaurants().stream().noneMatch(r -> r.getId().equals(restaurant.getId()))) {
      throw new UserUnauthorizedException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    RestaurantEntity restaurantEntity = mapper.toEntity(restaurant, userEntity);

    restaurantEntity.setName(restaurant.getName());

    RestaurantEntity savedRestaurant = repository.save(restaurantEntity);

    return mapper.toDomain(savedRestaurant);
  }

  @Override
  public Restaurant findById(UUID id) {
    return this.repository.findById(id)
        .map(mapper::toDomain)
        .orElse(null);
  }

  @Override
  public void deleteById(UUID id) {
    RestaurantEntity restaurant = this.repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

    this.repository.deleteById(restaurant.getId());
  }
}
