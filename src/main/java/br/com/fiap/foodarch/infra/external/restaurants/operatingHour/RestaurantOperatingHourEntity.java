package br.com.fiap.foodarch.infra.external.restaurants.operatingHour;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@ToString
public class RestaurantOperatingHourEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
  private RestaurantEntity restaurant;

  @Column(name = "day_of_week")
  @Enumerated(EnumType.STRING) // Ensure the enum is stored as a string
  private DayOfWeek dayOfWeek;

  @JsonFormat(pattern = "HH:mm:ss")
  @Column(name = "open_time")
  private LocalTime openTime;

  @JsonFormat(pattern = "HH:mm:ss")
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
