package br.com.fiap.foodarch.domain.entities.restaurants.assessment;

import java.util.UUID;

public class CreateRestaurantAssessmentFactory {

    public RestaurantAssessment createAssessment(UUID restaurant, UUID user, String comment, boolean like, int starts) {
        return RestaurantAssessment.createAssessment()
                .restaurantId(restaurant)
                .userId(user)
                .comment(comment)
                .like(like)
                .stars(starts)
                .build();
    }

}
