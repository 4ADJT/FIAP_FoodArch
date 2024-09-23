package br.com.fiap.foodarch.domain.entities.restaurants.operatingHour;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class RestaurantOperatingHours {

  private UUID id;

  private UUID restaurantId;

  private DayOfWeek dayOfWeek;

  @JsonFormat(pattern = "HH:mm:ss")
  private String openTime;

  @JsonFormat(pattern = "HH:mm:ss")
  private String closeTime;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder(builderClassName = "CreateRestaurantOperatingHourBuilder", builderMethodName = "createRestaurantOperatingHour")
  public RestaurantOperatingHours(
      UUID restaurantId,
      DayOfWeek dayOfWeek,
      String openTime,
      String closeTime
    ) {
    this.restaurantId = restaurantId;
    this.dayOfWeek = dayOfWeek;
    this.openTime = openTime;
    this.closeTime = closeTime;
  }

  @Builder(builderClassName = "UpdateRestaurantOperatingHourBuilder", builderMethodName = "updateRestaurantOperatingHour")
  public RestaurantOperatingHours(
      UUID id,
      UUID restaurantId,
      DayOfWeek dayOfWeek,
      String openTime,
      String closeTime,
      LocalDateTime createdAt
    ) {
    this.id = id;
    this.restaurantId = restaurantId;
    this.dayOfWeek = dayOfWeek;
    this.openTime = openTime;
    this.closeTime = closeTime;
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
