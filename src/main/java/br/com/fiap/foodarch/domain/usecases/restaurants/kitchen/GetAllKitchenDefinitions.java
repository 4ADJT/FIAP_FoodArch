package br.com.fiap.foodarch.domain.usecases.restaurants.kitchen;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.KitchenDefinitionRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;

import java.util.List;

public class GetAllKitchenDefinitions {
    private final KitchenDefinitionRepository repository;

    public GetAllKitchenDefinitions(KitchenDefinitionRepository repository) {
        this.repository = repository;
    }

    public List<KitchensDefinition> execute() {
        return repository.getAll();
    }
}
