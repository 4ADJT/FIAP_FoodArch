package br.com.fiap.foodarch.infra.external.restaurants.mv;

import br.com.fiap.foodarch.infra.external.restaurants.mv.support.RestaurantDataMVId;
import jakarta.persistence.*;

import lombok.Data;

import org.springframework.data.annotation.*;

import java.util.UUID;

@Entity
@Table(name = "restaurant_data_mv", schema = "public")
@Immutable
@Data
public class RestaurantDataMV {
  @EmbeddedId
  @AttributeOverride(name = "restaurant_id",
      column = @Column(name = "restaurant_id", insertable = false, updatable = false))
  @Column(name = "restaurant_id")
  private RestaurantDataMVId restaurant;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "restaurant_name")
  private String restaurantName;

  @Column(name = "address_street")
  private String addressStreet;

  @Column(name = "address_number")
  private String addressNumber;

  @Column(name = "address_neighborhood")
  private String addressNeighborhood;

  @Column(name = "address_city")
  private String addressCity;

  @Column(name = "address_state")
  private String addressState;

  @Column(name = "address_complement")
  private String addressComplement;

  @Column(name = "address_country")
  private String addressCountry;

  @Column(name = "address_zipcode")
  private String addressZipcode;

  private Object kitchens;

  private Object operation;

  private Object tables;

}

