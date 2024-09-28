package br.com.fiap.foodarch.infra.external.restaurants.kitchen;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.RestaurantKitchenRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.RestaurantKitchens;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantKitchenDefinition;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantKitchenRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.management.relation.InvalidRelationIdException;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaKitchenRepository implements RestaurantKitchenRepository {
  private final IRestaurantKitchenRepository repository;
  private final IRestaurantKitchenDefinition kitchenRepository;
  private final KitchenMapper mapper;
  private final IRestaurantRepository restaurantRepository;

  @Autowired
  public JpaKitchenRepository(IRestaurantKitchenRepository repository, IRestaurantRepository restaurantRepository,
                              IRestaurantKitchenDefinition kitchenRepository, KitchenMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
    this.restaurantRepository = restaurantRepository;
    this.kitchenRepository = kitchenRepository;
  }

  @Override
  public Page<RestaurantKitchens> getAll(Pageable pageable) {
    Page<KitchenEntity> all = this.repository.findAll(pageable);

    return all.map(mapper::toDomain);
  }

  @Override
  public Optional<RestaurantKitchens> getByRestaurantIdAndKitchenId(UUID restaurantId, UUID kitchenId) {
    KitchenEntity kitchenEntity = this.repository.findByRestaurantIdAndKitchenId(restaurantId, kitchenId)
        .orElse(null);

    if(kitchenEntity == null) {
      return Optional.empty();
    }

    return Optional.of(this.mapper.toDomain(kitchenEntity));
  }

  @Override
  public RestaurantKitchens save(RestaurantKitchens restaurantKitchens) {

    RestaurantEntity findRestaurant = this.restaurantRepository.findById(restaurantKitchens.getRestaurantId())
        .orElseThrow(
        () -> new RuntimeException("Restaurant not found.")
    );

    KitchenDefinitionEntity kitchen = this.kitchenRepository.findById(restaurantKitchens.getKitchenId())
        .orElseThrow(
        () -> new RuntimeException("Kitchen not found.")
    );

    KitchenEntity kitchenEntity = this.mapper.toEntity(findRestaurant, kitchen);

    try {
      kitchenEntity = this.repository.save(kitchenEntity);
    } catch (Exception e) {
      throw new RuntimeException("Failed to execute Restaurant Kitchen.");
    }

    return this.mapper.toDomain(kitchenEntity);
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

  @Override
  public void delete(UUID id) {
    this.repository.deleteById(id);
  }

}
