package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;

import br.com.fiap.foodarch.infra.external.restaurants.assessment.RestaurantAssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IRestaurantAssessment extends JpaRepository<RestaurantAssessmentEntity, UUID> {

    @Query("SELECT r FROM RestaurantAssessmentEntity r WHERE r.id = :id")
    RestaurantAssessmentEntity findByAssessmentId(UUID id);

    @Query("SELECT r FROM RestaurantAssessmentEntity r WHERE r.id = :id and r.restaurant.id = :RestaurantId and r.user.id = :userId")
    Optional<RestaurantAssessmentEntity> findByCommentLinked(UUID id, UUID RestaurantId, UUID userId);

    @Query("DELETE FROM RestaurantAssessmentEntity r WHERE r.id = :id and r.user.id = :userId")
    void deleteByIdAndUserId(UUID id, UUID userId);
}
