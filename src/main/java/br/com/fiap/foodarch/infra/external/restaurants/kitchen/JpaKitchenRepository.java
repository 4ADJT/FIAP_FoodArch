package br.com.fiap.foodarch.infra.external.restaurants.kitchen;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.RestaurantKitchenRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.RestaurantKitchens;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantKitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaKitchenRepository implements RestaurantKitchenRepository {
  private final IRestaurantKitchenRepository repository;
  private final KitchenMapper mapper;

  @Autowired
  public JpaKitchenRepository(IRestaurantKitchenRepository repository, KitchenMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Page<RestaurantKitchens> getAll(Pageable pageable) {
    Page<KitchenEntity> all = this.repository.findAll(pageable);

    return all.map(mapper::toDomain);
  }

  @Override
  public RestaurantKitchens getById(UUID restaurantId) {
    return null;
  }

  @Override
  public RestaurantKitchens save(RestaurantKitchens restaurantKitchens) {
    return null;
  }

  @Override
  public RestaurantKitchens update(RestaurantKitchens restaurantKitchens) {
    return null;
  }

  @Override
  public Page<RestaurantKitchens> searchByKitchenName(String kitchenName, Pageable pageable) {
    Page<KitchenEntity> kitchens = this.repository.findByKitchenName(kitchenName, pageable);

    return kitchens.map(mapper::toDomain);

  }

}
