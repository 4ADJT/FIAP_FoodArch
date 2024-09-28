package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.application.controller.restaurants.kitchens.SaveKitchenInRestaurantController;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.KitchenDefinitionRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.RestaurantKitchenRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.CreateRestaurantKitchenFactory;
import br.com.fiap.foodarch.domain.usecases.restaurants.kitchen.SaveRestaurantKitchen;
import br.com.fiap.foodarch.infra.external.restaurants.kitchen.KitchenMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectKitchen {

  @Bean
  public KitchenMapper kitchenMapper() {
    return new KitchenMapper();
  }

  @Bean
  public CreateRestaurantKitchenFactory createRestaurantKitchenFactory() {
    return new CreateRestaurantKitchenFactory();
  }

  @Bean
  public SaveRestaurantKitchen saveRestaurantKitchen(
                                        RestaurantKitchenRepository repository,
                                        RestaurantRepository restaurantRepository,
                                        CreateRestaurantKitchenFactory factory,
                                        KitchenDefinitionRepository kitchenRepository) {
    return new SaveRestaurantKitchen(repository, restaurantRepository, factory, kitchenRepository);
  }

  @Bean
  public SaveKitchenInRestaurantController saveKitchenInRestaurantController(SaveRestaurantKitchen saveRestaurantKitchen) {
    return new SaveKitchenInRestaurantController(saveRestaurantKitchen);
  }
}
