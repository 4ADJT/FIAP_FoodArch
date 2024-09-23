package br.com.fiap.foodarch.infra.external.restaurants.operatingHour;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantOperatingHourRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;

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

    RestaurantOperatingHourEntity restaurantOperatingHourEntityToUpdate = this.repository.findByRestaurantIdAndDayOfWeek(restaurantId, restaurantOperatingHour.getDayOfWeek());

    RestaurantOperatingHourEntity restaurantOperatingHourEntity = mapper.toEntity(restaurantOperatingHour,
        restaurantRef);  
    restaurantOperatingHourEntity.setId(restaurantOperatingHourEntityToUpdate.getId());
    restaurantOperatingHourEntity.setRestaurant(restaurantOperatingHourEntityToUpdate.getRestaurant());
    restaurantOperatingHourEntity.setCreatedAt(restaurantOperatingHourEntityToUpdate.getCreatedAt());

    RestaurantOperatingHourEntity toSave = repository.save(restaurantOperatingHourEntity);

    return mapper.toDomain(toSave);
  }

  @Override
  public List<RestaurantOperatingHours> findByRestaurantId(UUID restaurantId) {
    List<RestaurantOperatingHourEntity> restaurantOperatingHourEntity = this.repository
        .findByRestaurantId(restaurantId);

    List<RestaurantOperatingHours> responses = new ArrayList<>();
    for (RestaurantOperatingHourEntity restaurantOperatingHours : restaurantOperatingHourEntity) {
      responses.add(mapper.toDomain(restaurantOperatingHours));
    }

    return responses;
  }

  @Override
  public RestaurantOperatingHours findByRestaurantIdAndDayOfWeek(UUID restaurantId, DayOfWeek dayOfWeek) {
    RestaurantOperatingHourEntity restaurantOperatingHour = this.repository.findByRestaurantIdAndDayOfWeek(restaurantId, dayOfWeek);

    if (restaurantOperatingHour == null) {
      return null;
    }

    return mapper.toDomain(restaurantOperatingHour);
  }

  @Override
  public void deleteById(UUID id) {
    this.repository.deleteById(id);
  }

}
