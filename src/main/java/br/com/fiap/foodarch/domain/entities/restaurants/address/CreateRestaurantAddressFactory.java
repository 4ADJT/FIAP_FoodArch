package br.com.fiap.foodarch.domain.entities.restaurants.address;

import java.util.UUID;

public class CreateRestaurantAddressFactory {

  public RestaurantAddresses createRestaurantAddress(UUID restaurantId, String street, String number, String complement, String neighborhood, String city, String state, String country, String zipCode) {
    return RestaurantAddresses.createRestaurantAddress()
        .restaurantId(restaurantId)
        .street(street)
        .number(number)
        .complement(complement)
        .neighborhood(neighborhood)
        .city(city)
        .state(state)
        .country(country)
        .zipCode(zipCode)
        .build();
  }
}
