package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.infra.external.restaurants.address.RestaurantAddressMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectRestaurantAddressConfig {

  @Bean
  public RestaurantAddressMapper restaurantAddressMapper() {
    return new RestaurantAddressMapper();
  }
}
