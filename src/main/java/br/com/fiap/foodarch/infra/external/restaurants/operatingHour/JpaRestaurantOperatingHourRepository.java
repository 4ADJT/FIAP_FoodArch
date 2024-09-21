package br.com.fiap.foodarch.infra.external.restaurants.operatingHour;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantOperatingHourRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaRestaurantOperatingHourRepository implements RestaurantOperatingHourRepository {
  private final IRestaurantOperatingHourRepository repository;
  private final IRestaurantRepository restaurantRepository;
  private final RestaurantOperatingHourMapper mapper;

  @Autowired
  public JpaRestaurantOperatingHourRepository(
      IRestaurantOperatingHourRepository repository,
      IRestaurantRepository restaurantRepository,
      RestaurantOperatingHourMapper mapper) {
    this.repository = repository;
    this.restaurantRepository = restaurantRepository;
    this.mapper = mapper;
  }

  @Override
  public RestaurantOperatingHours createRestaurantOperatingHour(RestaurantOperatingHours restaurantOperatingHour,
      UUID restaurantId) {

    RestaurantEntity restaurantRef = this.restaurantRepository.getReferenceById(restaurantId);

    RestaurantOperatingHourEntity restaurantOperatingHourEntity = mapper.toEntity(restaurantOperatingHour,
        restaurantRef);

    RestaurantOperatingHourEntity toSave = repository.save(restaurantOperatingHourEntity);

    return mapper.toDomain(toSave);
  }

  @Override
  public RestaurantOperatingHours updateRestaurantOperatingHour(RestaurantOperatingHours restaurantOperatingHour,
      UUID restaurantId) {

    RestaurantEntity restaurantRef = this.restaurantRepository.getReferenceById(restaurantId);

    RestaurantOperatingHourEntity restaurantOperatingHourEntity = mapper.toEntity(restaurantOperatingHour,
        restaurantRef);

    RestaurantOperatingHourEntity toSave = repository.save(restaurantOperatingHourEntity);

    return mapper.toDomain(toSave);
  }

  @Override
  public RestaurantOperatingHours findByRestaurantId(UUID restaurantId) {
    RestaurantOperatingHourEntity restaurantOperatingHourEntity = this.repository.findByRestaurantId(restaurantId);

    return restaurantOperatingHourEntity != null ? mapper.toDomain(restaurantOperatingHourEntity) : null;
  }

  @Override
  public void deleteById(UUID id) {
    this.repository.deleteById(id);
  }
}
