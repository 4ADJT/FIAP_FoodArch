package br.com.fiap.foodarch.infra.external.restaurants.mv.support;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class RestaurantDataMVId implements Serializable {

  @Column(name = "restaurant_id")
  private UUID id;

}
