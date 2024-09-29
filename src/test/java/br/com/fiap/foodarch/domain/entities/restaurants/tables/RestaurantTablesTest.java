package br.com.fiap.foodarch.domain.entities.restaurants.tables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTablesTest {

    private RestaurantTables restaurantTable;

    @BeforeEach
    void setUp() {
        restaurantTable = new RestaurantTables();
    }

    @Test
    void onCreate() {
        restaurantTable.onCreate();
        assertNotNull(restaurantTable.getCreatedAt());
        assertNotNull(restaurantTable.getUpdatedAt());
    }

    @Test
    void onUpdate() {
        restaurantTable.onCreate();
        LocalDateTime originalUpdateTime = restaurantTable.getUpdatedAt();
        restaurantTable.onUpdate();
        assertTrue(restaurantTable.getUpdatedAt().isAfter(originalUpdateTime));
    }

    @Test
    void testEquals() {
        UUID id = UUID.randomUUID();
        RestaurantTables table1 = new RestaurantTables(id, UUID.randomUUID(), 1, true);
        RestaurantTables table2 = new RestaurantTables(id, UUID.randomUUID(), 1, true);
        assertEquals(table1, table2);
    }

    @Test
    void canEqual() {
        RestaurantTables table1 = new RestaurantTables();
        assertTrue(table1.canEqual(new RestaurantTables()));
    }

    @Test
    void testHashCode() {
        UUID id = UUID.randomUUID();
        RestaurantTables table1 = new RestaurantTables(id, UUID.randomUUID(), 1, true);
        RestaurantTables table2 = new RestaurantTables(id, UUID.randomUUID(), 1, true);
        assertEquals(table1.hashCode(), table2.hashCode());
    }

    @Test
    void getId() {
        UUID id = UUID.randomUUID();
        restaurantTable.setId(id);
        assertEquals(id, restaurantTable.getId());
    }

    @Test
    void getRestaurantId() {
        UUID restaurantId = UUID.randomUUID();
        restaurantTable.setRestaurantId(restaurantId);
        assertEquals(restaurantId, restaurantTable.getRestaurantId());
    }

    @Test
    void getTableNumber() {
        int tableNumber = 5;
        restaurantTable.setTableNumber(tableNumber);
        assertEquals(tableNumber, restaurantTable.getTableNumber());
    }

    @Test
    void isAvailable() {
        restaurantTable.setAvailable(true);
        assertTrue(restaurantTable.isAvailable());
    }

    @Test
    void getCreatedAt() {
        LocalDateTime createdAt = LocalDateTime.now();
        restaurantTable.setCreatedAt(createdAt);
        assertEquals(createdAt, restaurantTable.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        LocalDateTime updatedAt = LocalDateTime.now();
        restaurantTable.setUpdatedAt(updatedAt);
        assertEquals(updatedAt, restaurantTable.getUpdatedAt());
    }

    @Test
    void setId() {
        UUID id = UUID.randomUUID();
        restaurantTable.setId(id);
        assertEquals(id, restaurantTable.getId());
    }

    @Test
    void setRestaurantId() {
        UUID restaurantId = UUID.randomUUID();
        restaurantTable.setRestaurantId(restaurantId);
        assertEquals(restaurantId, restaurantTable.getRestaurantId());
    }

    @Test
    void setTableNumber() {
        int tableNumber = 10;
        restaurantTable.setTableNumber(tableNumber);
        assertEquals(tableNumber, restaurantTable.getTableNumber());
    }

    @Test
    void setAvailable() {
        restaurantTable.setAvailable(false);
        assertFalse(restaurantTable.isAvailable());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime createdAt = LocalDateTime.now();
        restaurantTable.setCreatedAt(createdAt);
        assertEquals(createdAt, restaurantTable.getCreatedAt());
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime updatedAt = LocalDateTime.now();
        restaurantTable.setUpdatedAt(updatedAt);
        assertEquals(updatedAt, restaurantTable.getUpdatedAt());
    }

    @Test
    void createRestaurantTable() {
        UUID restaurantId = UUID.randomUUID();
        RestaurantTables table = RestaurantTables.createRestaurantTable()
                .restaurantId(restaurantId)
                .tableNumber(5)
                .available(true)
                .build();
        assertEquals(restaurantId, table.getRestaurantId());
        assertEquals(5, table.getTableNumber());
        assertTrue(table.isAvailable());
    }

    @Test
    void updateRestaurantTable() {
        UUID id = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        RestaurantTables table = RestaurantTables.updateRestaurantTable()
                .id(id)
                .restaurantId(restaurantId)
                .tableNumber(5)
                .available(false)
                .build();
        assertEquals(id, table.getId());
        assertEquals(restaurantId, table.getRestaurantId());
        assertEquals(5, table.getTableNumber());
        assertFalse(table.isAvailable());
    }
}