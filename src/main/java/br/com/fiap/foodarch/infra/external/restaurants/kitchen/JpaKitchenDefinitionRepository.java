package br.com.fiap.foodarch.infra.external.restaurants.kitchen;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.KitchenDefinitionRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantKitchenDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaKitchenDefinitionRepository implements KitchenDefinitionRepository {
  private final IRestaurantKitchenDefinition repository;
  private final KitchenDefinitionMapper mapper;

  @Autowired
  public JpaKitchenDefinitionRepository(
      IRestaurantKitchenDefinition repository,
      KitchenDefinitionMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<KitchensDefinition> getAll() {
    List<KitchenDefinitionEntity> kitchens = this.repository.findAll();

    if(kitchens.isEmpty()) {
      return List.of();
    }

    return kitchens.stream().map(mapper::toDomain).collect(Collectors.toList());
  }
}
