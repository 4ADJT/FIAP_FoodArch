package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen;

import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;

import java.util.List;
import java.util.UUID;

public interface KitchenDefinitionRepository {
  List<KitchensDefinition> getAll();
  KitchensDefinition getById(UUID id);
}
