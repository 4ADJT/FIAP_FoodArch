package br.com.fiap.foodarch.domain.entities.restaurants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private Restaurant restaurant;
    private UUID id;
    private String name;
    private UUID ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        name = "Test Restaurant";
        ownerId = UUID.randomUUID();
        createdAt = LocalDateTime.now().minusDays(1);
        updatedAt = LocalDateTime.now().minusDays(1);

        restaurant = new Restaurant(id, name, ownerId, createdAt, updatedAt);
    }

    @Test
    void onCreate() {
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.onCreate();

        assertNotNull(newRestaurant.getCreatedAt());
        assertNotNull(newRestaurant.getUpdatedAt());
        assertEquals(newRestaurant.getCreatedAt(), newRestaurant.getUpdatedAt());
    }

    @Test
    void onUpdate() {
        restaurant.onUpdate();
        assertNotNull(restaurant.getUpdatedAt());
        assertNotEquals(restaurant.getCreatedAt(), restaurant.getUpdatedAt());
    }

    @Test
    void testEquals() {
        Restaurant otherRestaurant = new Restaurant(id, name, ownerId, createdAt, updatedAt);
        assertEquals(restaurant, otherRestaurant);
    }

    @Test
    void canEqual() {
        Restaurant otherRestaurant = new Restaurant();
        assertTrue(restaurant.canEqual(otherRestaurant));
    }

    @Test
    void testHashCode() {
        Restaurant otherRestaurant = new Restaurant(id, name, ownerId, createdAt, updatedAt);
        assertEquals(restaurant.hashCode(), otherRestaurant.hashCode());
    }

    @Test
    void getId() {
        assertEquals(id, restaurant.getId());
    }

    @Test
    void getName() {
        assertEquals(name, restaurant.getName());
    }

    @Test
    void getOwnerId() {
        assertEquals(ownerId, restaurant.getOwnerId());
    }

    @Test
    void getCreatedAt() {
        assertEquals(createdAt, restaurant.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(updatedAt, restaurant.getUpdatedAt());
    }

    @Test
    void setId() {
        UUID newId = UUID.randomUUID();
        restaurant.setId(newId);
        assertEquals(newId, restaurant.getId());
    }

    @Test
    void setName() {
        String newName = "Updated Restaurant";
        restaurant.setName(newName);
        assertEquals(newName, restaurant.getName());
    }

    @Test
    void setOwnerId() {
        UUID newOwnerId = UUID.randomUUID();
        restaurant.setOwnerId(newOwnerId);
        assertEquals(newOwnerId, restaurant.getOwnerId());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime newCreatedAt = LocalDateTime.now().minusDays(2);
        restaurant.setCreatedAt(newCreatedAt);
        assertEquals(newCreatedAt, restaurant.getCreatedAt());
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime newUpdatedAt = LocalDateTime.now().minusDays(2);
        restaurant.setUpdatedAt(newUpdatedAt);
        assertEquals(newUpdatedAt, restaurant.getUpdatedAt());
    }

    @Test
    void createRestaurant() {
        Restaurant createdRestaurant = Restaurant.createRestaurant()
                .name("New Restaurant")
                .ownerId(UUID.randomUUID())
                .build();

        assertNotNull(createdRestaurant.getName());
        assertNotNull(createdRestaurant.getOwnerId());
        assertNull(createdRestaurant.getId());  // As ID is not set in the create builder
    }

    @Test
    void updateRestaurant() {
        Restaurant updatedRestaurant = Restaurant.updateRestaurant()
                .id(id)
                .name("Updated Restaurant")
                .ownerId(ownerId)
                .createdAt(createdAt)
                .build();

        assertEquals("Updated Restaurant", updatedRestaurant.getName());
        assertEquals(ownerId, updatedRestaurant.getOwnerId());
        assertEquals(id, updatedRestaurant.getId());
    }
}
