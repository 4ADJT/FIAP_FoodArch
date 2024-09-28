package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;

import br.com.fiap.foodarch.infra.external.restaurants.kitchen.KitchenDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRestaurantKitchenDefinition extends JpaRepository<KitchenDefinitionEntity, UUID> {

}
