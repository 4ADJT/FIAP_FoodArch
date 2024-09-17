package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;

import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {
}
