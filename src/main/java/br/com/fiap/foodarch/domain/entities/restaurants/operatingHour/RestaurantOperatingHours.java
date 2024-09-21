package br.com.fiap.foodarch.domain.entities.restaurants.operatingHour;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class RestaurantOperatingHours {

  private UUID id;

  private UUID restaurantId;

  private DayOfWeek dayOfWeek;

  private LocalTime openTime;

  private LocalTime closeTime;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder(builderClassName = "CreateRestaurantOperatingHourBuilder", builderMethodName = "createRestaurantOperatingHour")
  public RestaurantOperatingHours(UUID restaurantId, DayOfWeek dayOfWeek, LocalTime openTime,
      LocalTime closeTime) {
    this.restaurantId = restaurantId;
    this.dayOfWeek = dayOfWeek;
    this.openTime = openTime;
    this.closeTime = closeTime;
  }

  @Builder(builderClassName = "UpdateRestaurantOperatingHourBuilder", builderMethodName = "updateRestaurantOperatingHour")
  public RestaurantOperatingHours(UUID id, UUID restaurantId,
      DayOfWeek dayOfWeek, LocalTime openTime,
      LocalTime closeTime, LocalDateTime createdAt) {
    this.id = id;
    this.restaurantId = restaurantId;
    this.dayOfWeek = dayOfWeek;
    this.openTime = openTime;
    this.closeTime = closeTime;
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
