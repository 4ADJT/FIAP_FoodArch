package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;

import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {

  @Query("SELECT r FROM RestaurantEntity r WHERE r.owner.id = :ownerId")
  Page<RestaurantEntity> findByOwnerId(UUID ownerId, Pageable pageable);

}
