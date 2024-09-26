package br.com.fiap.foodarch.domain.entities.restaurants.kitchens;

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
public class RestaurantKitchens {

  private UUID id;

  private UUID restaurantId;

  private UUID kitchenId;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder(builderClassName = "CreateRestaurantKitchensBuilder", builderMethodName = "createRestaurantKitchens")
  public RestaurantKitchens(UUID restaurantId, UUID kitchenId) {
    this.restaurantId = restaurantId;
    this.kitchenId = kitchenId;
  }

  @Builder(builderClassName = "UpdateRestaurantKitchensBuilder", builderMethodName = "updateRestaurantKitchens")
  public RestaurantKitchens(UUID id, UUID restaurantId, UUID kitchenId, LocalDateTime createdAt) {
    this.id = id;
    this.restaurantId = restaurantId;
    this.kitchenId = kitchenId;
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
