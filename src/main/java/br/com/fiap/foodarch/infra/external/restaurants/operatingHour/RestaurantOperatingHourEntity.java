package br.com.fiap.foodarch.infra.external.restaurants.operatingHour;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "operating_hours")
public class RestaurantOperatingHourEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
  private RestaurantEntity restaurant;

  @Column(name = "day_of_week")
  private DayOfWeek dayOfWeek;

  @Column(name = "open_time")
  private LocalTime openTime;

  @Column(name = "close_time")
  private LocalTime closeTime;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public RestaurantOperatingHourEntity(DayOfWeek dayOfWeek, LocalTime openTime, LocalTime closeTime, RestaurantEntity restaurant) {
    this.dayOfWeek = dayOfWeek;
    this.openTime = openTime;
    this.closeTime = closeTime;
    this.restaurant = restaurant;
  }
}
