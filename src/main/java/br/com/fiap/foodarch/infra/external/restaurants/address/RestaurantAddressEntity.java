package br.com.fiap.foodarch.infra.external.restaurants.address;

import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "addresses")
public class RestaurantAddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
  private RestaurantEntity restaurant;

  private String street;

  private String number;

  private String neighborhood;

  private String city;

  private String state;

  private String complement;

  private String country;

  @Column(name = "zip_code")
  private String zipCode;

  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime updated_at;

  public RestaurantAddressEntity(String street, String number, String neighborhood, String city, String state, String complement, String country, String zipCode, RestaurantEntity restaurant) {
    this.street = street;
    this.number = number;
    this.neighborhood = neighborhood;
    this.city = city;
    this.state = state;
    this.complement = complement;
    this.country = country;
    this.zipCode = zipCode;
    this.restaurant = restaurant;
  }
}
