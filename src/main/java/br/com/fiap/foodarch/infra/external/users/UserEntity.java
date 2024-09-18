package br.com.fiap.foodarch.infra.external.users;

import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @Column(unique = true, updatable = false, nullable = false)
  private String email;

  private LocalDate birthdate;

  @Column(unique = true, updatable = false, nullable = false)
  private String cpf;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<RestaurantEntity> restaurants = new ArrayList<>();

  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime updated_at;

  public UserEntity(UUID id, String name, String email, LocalDate birthdate, String cpf, LocalDateTime created_at, LocalDateTime updated_at) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.birthdate = birthdate;
    this.cpf = cpf;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }
}
