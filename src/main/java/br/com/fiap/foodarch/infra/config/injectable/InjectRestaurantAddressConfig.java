package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.application.controller.restaurants.address.CreateRestaurantAddressController;
import br.com.fiap.foodarch.application.controller.restaurants.address.GetRestaurantAddressByIdController;
import br.com.fiap.foodarch.application.controller.restaurants.address.UpdateRestaurantAddressController;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address.RestaurantAddressRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.address.CreateRestaurantAddressFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.address.UpdateRestaurantAddressFactory;
import br.com.fiap.foodarch.domain.usecases.restaurants.address.CreateRestaurantAddress;
import br.com.fiap.foodarch.domain.usecases.restaurants.address.GetRestaurantById;
import br.com.fiap.foodarch.domain.usecases.restaurants.address.UpdateRestaurantAddress;
import br.com.fiap.foodarch.infra.external.restaurants.address.RestaurantAddressMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectRestaurantAddressConfig {

  @Bean
  public RestaurantAddressMapper restaurantAddressMapper() {
    return new RestaurantAddressMapper();
  }

  @Bean
  public CreateRestaurantAddressFactory createRestaurantAddressFactory() {
    return new CreateRestaurantAddressFactory();
  }

  @Bean
  public CreateRestaurantAddress createRestaurantAddress(
      RestaurantAddressRepository repository,
      CreateRestaurantAddressFactory factory,
      RestaurantRepository restaurantRepository
  ) {
    return new CreateRestaurantAddress(repository, factory, restaurantRepository
    );
  }

  @Bean
  public GetRestaurantById getRestaurantById(RestaurantAddressRepository repository) {
    return new GetRestaurantById(repository);
  }

  @Bean
  public GetRestaurantAddressByIdController getRestaurantAddressByIdController(GetRestaurantById getRestaurantById) {
    return new GetRestaurantAddressByIdController(getRestaurantById);
  }

  @Bean
  public CreateRestaurantAddressController createRestaurantAddressController(CreateRestaurantAddress createRestaurantAddress) {
    return new CreateRestaurantAddressController(createRestaurantAddress);
  }

  @Bean
  public UpdateRestaurantAddressFactory updateRestaurantAddressFactory() {
    return new UpdateRestaurantAddressFactory();
  }

  @Bean
  public UpdateRestaurantAddress updateRestaurantAddress(
    RestaurantAddressRepository repository,
    UpdateRestaurantAddressFactory factory,
    RestaurantRepository restaurantRepository
  ) {
    return new UpdateRestaurantAddress(repository, factory, restaurantRepository);
  }

  @Bean
  public UpdateRestaurantAddressController updateRestaurantAddressController(UpdateRestaurantAddress updateRestaurantAddress) {
    return new UpdateRestaurantAddressController(updateRestaurantAddress);
  }
}
