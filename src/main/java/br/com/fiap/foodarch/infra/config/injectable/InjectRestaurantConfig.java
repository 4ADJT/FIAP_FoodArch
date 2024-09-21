package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.application.controller.restaurants.CreateRestaurantController;
import br.com.fiap.foodarch.application.controller.restaurants.GetAllRestaurantsController;
import br.com.fiap.foodarch.application.controller.restaurants.UpdateRestaurantNameController;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.UpdateRestaurantFactory;
import br.com.fiap.foodarch.domain.usecases.restaurants.GetAllRestaurants;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.CreateRestaurantFactory;
import br.com.fiap.foodarch.domain.usecases.restaurants.CreateRestaurant;
import br.com.fiap.foodarch.domain.usecases.restaurants.UpdateRestaurantName;
import br.com.fiap.foodarch.infra.external.restaurants.JpaRestaurantRepository;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntityMapper;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.users.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectRestaurantConfig {

  @Bean
  JpaRestaurantRepository jpaRestaurantRepository(IRestaurantRepository repository,
                                                  IUserRepository userRepository,
                                                  RestaurantEntityMapper mapper) {
      return new JpaRestaurantRepository(repository, userRepository, mapper);
  }

  @Bean
  public RestaurantEntityMapper restaurantEntityMapper() {
      return new RestaurantEntityMapper();
  }

  @Bean
  public CreateRestaurantFactory createRestaurantFactory() {
      return new CreateRestaurantFactory();
  }

  @Bean
  public CreateRestaurant createRestaurant(RestaurantRepository repository,
                                           UserRepository userRepository,
                                           CreateRestaurantFactory factory) {
      return new CreateRestaurant(repository, userRepository, factory);
  }

  @Bean
  public CreateRestaurantController createRestaurantController(CreateRestaurant createRestaurant) {
      return new CreateRestaurantController(createRestaurant);
  }

  @Bean
  public GetAllRestaurants getAllRestaurants(RestaurantRepository repository) {
      return new GetAllRestaurants(repository);
  }

  @Bean
  public GetAllRestaurantsController getAllRestaurantsController(GetAllRestaurants getAllRestaurants) {
      return new GetAllRestaurantsController(getAllRestaurants);
  }

  @Bean
  public UpdateRestaurantFactory updateRestaurantFactory() {
      return new UpdateRestaurantFactory();
  }

  @Bean
  public UpdateRestaurantName updateRestaurantName(RestaurantRepository repository,
                                                   UpdateRestaurantFactory factory) {
      return new UpdateRestaurantName(repository, factory);
  }

  @Bean
  public UpdateRestaurantNameController updateRestaurantNameController(UpdateRestaurantName updateRestaurantName) {
      return new UpdateRestaurantNameController(updateRestaurantName);
  }
}
