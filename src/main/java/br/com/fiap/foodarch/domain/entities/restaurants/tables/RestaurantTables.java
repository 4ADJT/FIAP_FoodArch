package br.com.fiap.foodarch.domain.entities.restaurants.tables;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RestaurantTables {
    private UUID id;

    private UUID restaurantId;

    private int tableNumber;

    private boolean available;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder(builderClassName = "CreateRestaurantTable", builderMethodName = "createRestaurantTable")
    public RestaurantTables(UUID restaurantId, int tableNumber, boolean available) {
        this.restaurantId = restaurantId;
        this.tableNumber = tableNumber;
        this.available = available;
    }

    @Builder(builderClassName = "UpdateRestaurantTable", builderMethodName = "updateRestaurantTable")
    public RestaurantTables(UUID id, UUID restaurantId, int tableNumber, boolean available) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.tableNumber = tableNumber;
        this.available = available;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
