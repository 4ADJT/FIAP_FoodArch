package br.com.fiap.foodarch.domain.entities.restaurants.address;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateRestaurantAddressFactory {
  public RestaurantAddresses updateRestaurantAddress(UUID id, UUID restaurantId, String street, String number, String complement, String neighborhood, String city, String state, String country, String zipCode, LocalDateTime createdAt) {
    return RestaurantAddresses.updateRestaurantAddress()
        .id(id)
        .restaurantId(restaurantId)
        .street(street)
        .number(number)
        .complement(complement)
        .neighborhood(neighborhood)
        .city(city)
        .state(state)
        .country(country)
        .zipCode(zipCode)
        .createdAt(createdAt)
        .build();
  }
}
