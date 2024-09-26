package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen;

import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;

import java.util.List;

public interface KitchenDefinitionRepository {
  List<KitchensDefinition> getAll();
}
