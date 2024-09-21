package br.com.fiap.foodarch.infra.external.restaurants.address;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;

public class RestaurantAddressMapper {

  public RestaurantAddressEntity toEntity(RestaurantAddresses address, RestaurantEntity restaurant) {
    return new RestaurantAddressEntity(
        address.getStreet(),
        address.getNumber(),
        address.getNeighborhood(),
        address.getCity(),
        address.getState(),
        address.getComplement(),
        address.getCountry(),
        address.getZipCode(),
        restaurant
    );
  }

  public RestaurantAddresses toDomain(RestaurantAddressEntity entity) {
    return new RestaurantAddresses(
        entity.getId(),
        entity.getRestaurant().getId(),
        entity.getStreet(),
        entity.getNumber(),
        entity.getNeighborhood(),
        entity.getCity(),
        entity.getState(),
        entity.getComplement(),
        entity.getCountry(),
        entity.getZipCode(),
        entity.getCreated_at(),
        entity.getUpdated_at()
    );
  }

}
