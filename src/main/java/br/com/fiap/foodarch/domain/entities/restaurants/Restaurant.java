package br.com.fiap.foodarch.domain.entities.restaurants;

import br.com.fiap.foodarch.domain.entities.users.User;
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
public class Restaurant {

  private UUID id;

  private String name;

  private UUID ownerId;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder(builderClassName = "CreateRestaurantBuilder", builderMethodName = "createRestaurant")
  public Restaurant(String name, UUID ownerId) {
    this.name = name;
    this.ownerId = ownerId;
  }

  @Builder(builderClassName = "UpdateRestaurantBuilder", builderMethodName = "updateRestaurant")
  public Restaurant(UUID id, String name, UUID ownerId, LocalDateTime createdAt) {
    this.id = id;
    this.name = name;
    this.ownerId = ownerId;
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
