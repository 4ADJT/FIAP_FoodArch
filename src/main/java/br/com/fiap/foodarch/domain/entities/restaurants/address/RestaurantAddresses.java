package br.com.fiap.foodarch.domain.entities.restaurants.address;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RestaurantAddresses {

  private UUID id;

  private UUID restaurantId;

  private String street;

  private String number;

  private String neighborhood;

  private String city;

  private String state;

  private String complement;

  private String country;

  private String zipCode;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder(builderClassName = "CreateRestaurantAddressBuilder", builderMethodName = "createRestaurantAddress")
  public RestaurantAddresses(UUID restaurantId, String street, String number, String neighborhood, String city, String state, String complement, String country, String zipCode) {
    this.restaurantId = restaurantId;
    this.street = street;
    this.number = number;
    this.neighborhood = neighborhood;
    this.city = city;
    this.state = state;
    this.complement = complement;
    this.country = country;
    this.zipCode = zipCode;
  }

  @Builder(builderClassName = "UpdateRestaurantAddressBuilder", builderMethodName = "updateRestaurantAddress")
  public RestaurantAddresses(UUID id, UUID restaurantId, String street, String number, String neighborhood, String city, String state, String complement, String country, String zipCode, LocalDateTime createdAt) {
    this.id = id;
    this.restaurantId = restaurantId;
    this.street = street;
    this.number = number;
    this.neighborhood = neighborhood;
    this.city = city;
    this.state = state;
    this.complement = complement;
    this.country = country;
    this.zipCode = zipCode;
    this.createdAt = createdAt;
  }

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
