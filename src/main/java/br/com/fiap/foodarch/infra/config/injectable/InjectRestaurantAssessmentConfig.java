package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.CreateRestaurantAssessmentFactory;
import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.*;
import br.com.fiap.foodarch.infra.external.restaurants.assessment.RestaurantAssessmentEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectRestaurantAssessmentConfig {

  @Bean
  public RestaurantAssessmentEntityMapper restaurantAssessmentMapper() {
    return new RestaurantAssessmentEntityMapper();
  }

  @Bean
  public CreateRestaurantAssessmentFactory createAssessmentFactory() {
    return new CreateRestaurantAssessmentFactory();
  }

  @Bean
  public CreateRestaurantAssessment createRestaurantAssessment(
      RestaurantAssessmentRepository repository,
      CreateRestaurantAssessmentFactory factory

  ) {
    return new CreateRestaurantAssessment(repository, factory);
  }

  @Bean
  public RestaurantDeleteAssessment deleteAssessment(RestaurantAssessmentRepository repository) {
    return new RestaurantDeleteAssessment(repository);
  }

  @Bean
  public GetRestaurantAssessmentById GetRestaurantAssessmentById(RestaurantAssessmentRepository repository) {
    return new GetRestaurantAssessmentById(repository);
  }

  @Bean
  public GetRestaurantAllAssessment getAllAssessment(RestaurantAssessmentRepository repository) {
    return new GetRestaurantAllAssessment(repository);
  }

  @Bean
  public UpdateRestaurantAssessment updateAssessment(
          RestaurantAssessmentRepository repository,
          CreateRestaurantAssessmentFactory factory
  ) {
    return new UpdateRestaurantAssessment(repository, factory);
  }

}
