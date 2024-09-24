package br.com.fiap.foodarch.infra.external.restaurants.tables;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RestaurantTablesEntity {

    private UUID id;

    private UUID restaurantId;

    private int tableNumber;

    private boolean available;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public RestaurantTablesEntity(UUID id, UUID restaurantId, int tableNumber, boolean available) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.tableNumber = tableNumber;
        this.available = available;
    }
}
