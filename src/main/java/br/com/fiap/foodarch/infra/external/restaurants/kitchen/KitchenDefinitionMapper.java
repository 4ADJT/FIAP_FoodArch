package br.com.fiap.foodarch.infra.external.restaurants.kitchen;

import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;

public class KitchenDefinitionMapper {

  public KitchensDefinition toDomain(KitchenDefinitionEntity entity) {
    KitchensDefinition kitchens = new KitchensDefinition();
    kitchens.setId(entity.getId());
    kitchens.setName(entity.getName());
    kitchens.setCreatedAt(entity.getCreated_at());
    kitchens.setUpdatedAt(entity.getUpdated_at());

    return kitchens;
  }
  public KitchenDefinitionEntity toEntity(KitchensDefinition domain) {
    KitchenDefinitionEntity entity = new KitchenDefinitionEntity();
    entity.setId(domain.getId());
    entity.setName(domain.getName());
    entity.setCreated_at(domain.getCreatedAt());
    entity.setUpdated_at(domain.getUpdatedAt());

    return entity;
  }

}
