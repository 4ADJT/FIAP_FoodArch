package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address;

import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;

import java.util.UUID;

public interface RestaurantAddressRepository {
  RestaurantAddresses createRestaurantAddress(RestaurantAddresses restaurantAddress, UUID restaurantId);

  RestaurantAddresses updateRestaurantAddress(RestaurantAddresses restaurantAddress, UUID restaurantId);

  RestaurantAddresses findByRestaurantId(UUID restaurantId);

  void deleteById(UUID id);
}
