package br.com.fiap.foodarch.infra.external.restaurants.tables;

import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "restaurant_tables")
public class RestaurantTablesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private RestaurantEntity restaurant;

    private int tableNumber;

    private boolean available;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public RestaurantTablesEntity(UUID id, RestaurantEntity restaurant, int tableNumber, boolean available) {
        this.id = id;
        this.restaurant = restaurant;
        this.tableNumber = tableNumber;
        this.available = available;
    }
}
