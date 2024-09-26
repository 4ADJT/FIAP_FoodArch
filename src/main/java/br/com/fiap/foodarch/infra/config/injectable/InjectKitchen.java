package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.infra.external.restaurants.kitchen.KitchenMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectKitchen {

  @Bean
  public KitchenMapper kitchenMapper() {
    return new KitchenMapper();
  }

}
