package br.com.fiap.foodarch.infra.config.injectable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.CreateRestaurantOperatingHourFactory;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.CreateRestaurantOperatingHour;
import br.com.fiap.foodarch.infra.external.restaurants.operatingHour.RestaurantOperatingHourMapper;

@Configuration
public class InjectRestaurantOperatingHourConfig {

  @Bean
  public RestaurantOperatingHourMapper restaurantOperatingHourMapper() {
    return new RestaurantOperatingHourMapper();
  }

  @Bean
  public CreateRestaurantOperatingHour createRestaurantOperatingHour(
      RestaurantOperatingHourRepository repository,
      UserRepository userRepository,
      RestaurantRepository restaurantRepository,
      CreateRestaurantOperatingHourFactory factory) {
    return new CreateRestaurantOperatingHour(repository, userRepository, restaurantRepository, factory);
  }

}
