package br.com.fiap.foodarch.application.presenters.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressOutput;

public class RestaurantAddressPresenter {
  public static RestaurantAddressOutput restaurantAddressResponse(RestaurantAddresses restaurantAddress) {
    return new RestaurantAddressOutput(
        restaurantAddress.getId(),
        restaurantAddress.getStreet(),
        restaurantAddress.getRestaurantId(),
        restaurantAddress.getNumber(),
        restaurantAddress.getNeighborhood(),
        restaurantAddress.getCity(),
        restaurantAddress.getState(),
        restaurantAddress.getComplement(),
        restaurantAddress.getCountry(),
        restaurantAddress.getZipCode(),
        restaurantAddress.getCreatedAt()
      );
  }
}
