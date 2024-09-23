package br.com.fiap.foodarch.infra.external.restaurants.address;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address.RestaurantAddressRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantAddressRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaRestaurantAddressRepository implements RestaurantAddressRepository {
  private final IRestaurantAddressRepository repository;
  private final IRestaurantRepository restaurantRepository;
  private final RestaurantAddressMapper mapper;

  @Autowired
  public JpaRestaurantAddressRepository(
      IRestaurantAddressRepository repository,
      IRestaurantRepository restaurantRepository,
      RestaurantAddressMapper mapper
  ) {
    this.repository = repository;
    this.restaurantRepository = restaurantRepository;
    this.mapper = mapper;
  }

  @Override
  public RestaurantAddresses createRestaurantAddress(RestaurantAddresses restaurantAddress,
                                                     UUID restaurantId) {

    RestaurantEntity restaurantRef = this.restaurantRepository.getReferenceById(restaurantId);

    RestaurantAddressEntity restaurantAddressEntity = mapper.toEntity(restaurantAddress, restaurantRef);

    RestaurantAddressEntity toSave = repository.save(restaurantAddressEntity);

    return mapper.toDomain(toSave);
  }

  @Override
  public RestaurantAddresses updateRestaurantAddress(RestaurantAddresses restaurantAddress,
                                                     UUID restaurantId) {
    RestaurantEntity restaurantRef = this.restaurantRepository.getReferenceById(restaurantId);

    RestaurantAddressEntity restaurantToUpdate = this.repository.findByRestaurantId(restaurantId);

    RestaurantAddressEntity restaurantAddressEntity = mapper.toEntity(restaurantAddress, restaurantRef);

    restaurantAddressEntity.setRestaurant(restaurantToUpdate.getRestaurant());
    restaurantAddressEntity.setId(restaurantToUpdate.getId());
    restaurantAddressEntity.setCreated_at(restaurantToUpdate.getCreated_at());

    RestaurantAddressEntity toSave = repository.save(restaurantAddressEntity);

    return mapper.toDomain(toSave);
  }

  @Override
  public RestaurantAddresses findByRestaurantId(UUID restaurantId) {

    RestaurantAddressEntity restaurantAddressEntity = this.repository.findByRestaurantId(restaurantId);

    if (restaurantAddressEntity == null) {
      return null;
    }

    return mapper.toDomain(restaurantAddressEntity);
  }

  @Override
  public void deleteById(UUID id) {
    this.repository.deleteById(id);
  }
}
