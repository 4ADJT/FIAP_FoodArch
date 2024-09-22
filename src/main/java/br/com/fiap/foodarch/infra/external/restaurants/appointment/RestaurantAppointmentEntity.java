package br.com.fiap.foodarch.infra.external.restaurants.appointment;

import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "appointment")
public class RestaurantAppointmentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
  private RestaurantEntity restaurant;

//  TODO: add o table
//  @OneToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "table_id", referencedColumnName = "id", nullable = false)
//  private TableEntity table;

  private LocalDateTime reservation_date;

  private LocalTime start_time;

  private LocalTime end_time;

  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime updated_at;



  public RestaurantAppointmentEntity(
          LocalDateTime reservation_date,
          LocalTime start_time,
          LocalTime end_time,
          RestaurantEntity restaurant
//        TODO: add a entidade da mesa | TableEntity table
  ) {
    this.reservation_date = reservation_date;
    this.start_time = start_time;
    this.end_time = end_time;
    this.restaurant = restaurant;
//  TODO: add a entidade da mesa |  this.table = table;
  }
}
