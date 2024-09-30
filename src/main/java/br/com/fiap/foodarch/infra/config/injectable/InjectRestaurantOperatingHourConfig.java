package br.com.fiap.foodarch.infra.config.injectable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.foodarch.application.controller.restaurants.operatingHour.GetRestaurantOperatingHourByIdController;
import br.com.fiap.foodarch.application.controller.restaurants.operatingHour.UpdateRestaurantOperatingHourController;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.CreateRestaurantOperatingHourFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.UpdateRestaurantOperatingHourFactory;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.CreateRestaurantOperatingHour;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.GetRestaurantOperatingHourById;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.UpdateRestaurantOperatingHour;
import br.com.fiap.foodarch.infra.external.restaurants.operatingHour.RestaurantOperatingHourMapper;

@Configuration
public class InjectRestaurantOperatingHourConfig {

  @Bean
  public RestaurantOperatingHourMapper restaurantOperatingHourMapper() {
    return new RestaurantOperatingHourMapper();
  }

   @Bean
  public CreateRestaurantOperatingHourFactory createRestaurantOperatingHourFactory() {
    return new CreateRestaurantOperatingHourFactory();
  }

  @Bean
  public CreateRestaurantOperatingHour createRestaurantOperatingHour(
      RestaurantOperatingHourRepository repository,
      UserRepository userRepository,
      RestaurantRepository restaurantRepository,
      CreateRestaurantOperatingHourFactory factory
  ) {
    return new CreateRestaurantOperatingHour(repository, userRepository, restaurantRepository, factory);
  }

  @Bean
  public GetRestaurantOperatingHourById getRestaurantOperatingHourById(RestaurantOperatingHourRepository repository) {
    return new GetRestaurantOperatingHourById(repository);
  }

  @Bean
  public GetRestaurantOperatingHourByIdController getRestaurantOperatingHourByIdController(GetRestaurantOperatingHourById getRestaurantById) {
    return new GetRestaurantOperatingHourByIdController(getRestaurantById);
  }

  @Bean
  public UpdateRestaurantOperatingHourFactory updateRestaurantOperatingHourFactory() {
    return new UpdateRestaurantOperatingHourFactory();
  }

  @Bean
  public UpdateRestaurantOperatingHour updateRestaurantOperatingHour(
    RestaurantOperatingHourRepository repository,
    UserRepository userRepository,
    RestaurantRepository restaurantRepository,
    UpdateRestaurantOperatingHourFactory factory
  ) {
    return new UpdateRestaurantOperatingHour(repository, userRepository, restaurantRepository, factory);
  }

  @Bean
  public UpdateRestaurantOperatingHourController updateRestaurantOperatingHourController(UpdateRestaurantOperatingHour updateRestaurantOperatingHour) {
    return new UpdateRestaurantOperatingHourController(updateRestaurantOperatingHour);
  }

}
