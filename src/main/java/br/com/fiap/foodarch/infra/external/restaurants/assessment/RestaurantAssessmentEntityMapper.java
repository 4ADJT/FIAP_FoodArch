package br.com.fiap.foodarch.infra.external.restaurants.assessment;

import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.external.users.UserEntity;

public class RestaurantAssessmentEntityMapper {

    public RestaurantAssessmentEntity toEntity(final RestaurantAssessment assessment, RestaurantEntity restaurantEntity, UserEntity userEntity) {
        return new RestaurantAssessmentEntity(
                userEntity,
                restaurantEntity,
                assessment.getComment(),
                assessment.isLike(),
                assessment.getStars()
        );
    }

    public RestaurantAssessment toDomain(final RestaurantAssessmentEntity restaurantAssessmentEntity) {
        return new RestaurantAssessment(
                restaurantAssessmentEntity.getId(),
                restaurantAssessmentEntity.getUser().getId(),
                restaurantAssessmentEntity.getRestaurant().getId(),
                restaurantAssessmentEntity.getComment(),
                restaurantAssessmentEntity.is_like(),
                restaurantAssessmentEntity.getStars(),
                restaurantAssessmentEntity.getCreated_at(),
                restaurantAssessmentEntity.getUpdated_at()
        );
    }

}
