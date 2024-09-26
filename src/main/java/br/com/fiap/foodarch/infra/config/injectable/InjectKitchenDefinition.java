package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.application.controller.restaurants.kitchens.GetAllKitchenDefinitionController;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.KitchenDefinitionRepository;
import br.com.fiap.foodarch.domain.usecases.restaurants.kitchen.GetAllKitchenDefinitions;
import br.com.fiap.foodarch.infra.external.restaurants.kitchen.JpaKitchenDefinitionRepository;
import br.com.fiap.foodarch.infra.external.restaurants.kitchen.KitchenDefinitionMapper;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantKitchenDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectKitchenDefinition {
  @Bean
  public KitchenDefinitionMapper kitchenDefinitionMapper() {
    return new KitchenDefinitionMapper();
  }

  @Bean
  public GetAllKitchenDefinitions getAllKitchenDefinitions(KitchenDefinitionRepository repository) {
    return new GetAllKitchenDefinitions(repository);
  }

  @Bean
  public GetAllKitchenDefinitionController getAllKitchenDefinitionController(GetAllKitchenDefinitions getAllKitchenDefinitions) {
    return new GetAllKitchenDefinitionController(getAllKitchenDefinitions);
  }

}
