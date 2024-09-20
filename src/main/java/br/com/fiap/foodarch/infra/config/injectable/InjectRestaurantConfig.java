package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectRestaurantConfig {

  @Bean
  public RestaurantEntityMapper restaurantEntityMapper() {
      return new RestaurantEntityMapper();
  }

}
