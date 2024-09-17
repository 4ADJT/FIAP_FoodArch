package br.com.fiap.foodarch.infra.external.restaurants;

import br.com.fiap.foodarch.infra.external.users.UserEntity;
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
@Table(name = "restaurants")
public class RestaurantEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
  private UserEntity owner;

  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime updated_at;

  public RestaurantEntity(String name, UUID owner_id, UUID id, LocalDateTime created_at, LocalDateTime updated_at) {
    this.name = name;
    this.id = id;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.owner.setId(owner_id);
  }
}
