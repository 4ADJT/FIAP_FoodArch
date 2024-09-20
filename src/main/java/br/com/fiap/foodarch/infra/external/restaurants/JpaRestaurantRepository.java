package br.com.fiap.foodarch.infra.external.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.infra.external.users.UserEntity;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaRestaurantRepository implements RestaurantRepository {
  private final IRestaurantRepository repository;
  private final RestaurantEntityMapper mapper;

  @Autowired
  public JpaRestaurantRepository(
      IRestaurantRepository repository,
      RestaurantEntityMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Restaurant createRestaurant(Restaurant restaurant, UserEntity owner) {
    RestaurantEntity restaurantEntity = mapper.toEntity(restaurant, owner);

    restaurantEntity = repository.save(restaurantEntity);

    restaurant = mapper.toDomain(restaurantEntity);

    return restaurant;
  }

  @Override
  public Page<Restaurant> listRestaurants(Pageable pageable) {
    return null;
  }

  @Override
  public Page<Restaurant> findByOwnerId(UUID ownerId, Pageable pageable) {
    return null;
  }

  @Override
  public Restaurant updateRestaurant(Restaurant restaurant, UserEntity owner) {
    return null;
  }

  @Override
  public Restaurant findById(UUID id) {
    return null;
  }
}
