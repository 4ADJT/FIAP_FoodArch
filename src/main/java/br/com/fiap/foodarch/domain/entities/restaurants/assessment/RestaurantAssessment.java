package br.com.fiap.foodarch.domain.entities.restaurants.assessment;

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
public class RestaurantAssessment {
  private UUID id;

  private UUID userId;

  private UUID restaurantId;

  private String comment;

  private boolean like;

  private int stars;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder(builderClassName = "CreateAssessmentBuilder", builderMethodName = "createAssessment")
  public RestaurantAssessment(UUID userId, UUID restaurantId, String comment, boolean like, int stars) {
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.comment = comment;
    this.like = like;
    this.stars = stars;
  }

  @Builder(builderClassName = "UpdateAssessmentBuilder", builderMethodName = "updateAssessment")
  public RestaurantAssessment(UUID id, String comment, boolean like, int stars) {
    this.id = id;
    this.comment = comment;
    this.like = like;
    this.stars = stars;
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
