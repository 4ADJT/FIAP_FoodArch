package br.com.fiap.foodarch.domain.entities.restaurants.kitchens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantKitchensTest {

  private RestaurantKitchens restaurantKitchens;

  @BeforeEach
  public void setup() {
    restaurantKitchens = RestaurantKitchens.createRestaurantKitchens()
        .restaurantId(UUID.randomUUID())
        .kitchenId(UUID.randomUUID())
        .build();
  }

  @Test
  public void testOnCreate() {
    // Act
    restaurantKitchens.onCreate();

    // Assert
    assertNotNull(restaurantKitchens.getCreatedAt());
    assertNotNull(restaurantKitchens.getUpdatedAt());
  }

  @Test
  public void testOnUpdate() {
    // Arrange
    restaurantKitchens.onCreate(); // Simulate initial creation
    LocalDateTime originalCreatedAt = restaurantKitchens.getCreatedAt();

    // Act
    restaurantKitchens.onUpdate();

    // Assert
    assertNotNull(restaurantKitchens.getUpdatedAt());
  }

  @Test
  public void testCreateRestaurantKitchensBuilder() {
    // Arrange
    UUID restaurantId = UUID.randomUUID();
    UUID kitchenId = UUID.randomUUID();

    // Act
    RestaurantKitchens newKitchens = RestaurantKitchens.createRestaurantKitchens()
        .restaurantId(restaurantId)
        .kitchenId(kitchenId)
        .build();

    // Assert
    assertEquals(restaurantId, newKitchens.getRestaurantId());
    assertEquals(kitchenId, newKitchens.getKitchenId());
    assertNull(newKitchens.getCreatedAt()); // Not created yet
    assertNull(newKitchens.getUpdatedAt());
  }

  @Test
  public void testUpdateRestaurantKitchensBuilder() {
    // Arrange
    UUID id = UUID.randomUUID();
    UUID restaurantId = UUID.randomUUID();
    UUID kitchenId = UUID.randomUUID();
    LocalDateTime createdAt = LocalDateTime.now();

    // Act
    RestaurantKitchens updatedKitchens = RestaurantKitchens.updateRestaurantKitchens()
        .id(id)
        .restaurantId(restaurantId)
        .kitchenId(kitchenId)
        .createdAt(createdAt)
        .build();

    // Assert
    assertEquals(id, updatedKitchens.getId());
    assertEquals(restaurantId, updatedKitchens.getRestaurantId());
    assertEquals(kitchenId, updatedKitchens.getKitchenId());
    assertEquals(createdAt, updatedKitchens.getCreatedAt());
    assertNull(updatedKitchens.getUpdatedAt()); // Only updated on update lifecycle
  }
}
