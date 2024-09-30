package br.com.fiap.foodarch.domain.entities.restaurants.operation;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantOperationTest {

    private RestaurantOperation restaurantOperation;
    private UUID id;
    private UUID restaurantId;
    private DayOfWeek dayOfWeek;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        restaurantId = UUID.randomUUID();
        dayOfWeek = DayOfWeek.MONDAY;
        openTime = LocalTime.of(8, 0);
        closeTime = LocalTime.of(22, 0);
        createdAt = LocalDateTime.now().minusDays(1);
        updatedAt = LocalDateTime.now().minusDays(1);

        restaurantOperation = new RestaurantOperation(id, restaurantId, dayOfWeek, openTime, closeTime, createdAt, updatedAt);
    }

    @Test
    void onUpdate() {
        restaurantOperation.onUpdate();
        assertNotNull(restaurantOperation.getUpdatedAt());
        assertNotEquals(restaurantOperation.getCreatedAt(), restaurantOperation.getUpdatedAt());
    }

    @Test
    void testEquals() {
        RestaurantOperation otherOperation = new RestaurantOperation(id, restaurantId, dayOfWeek, openTime, closeTime, createdAt, updatedAt);
        assertEquals(restaurantOperation, otherOperation);
    }

    @Test
    void canEqual() {
        RestaurantOperation otherOperation = new RestaurantOperation();
        assertTrue(restaurantOperation.canEqual(otherOperation));
    }

    @Test
    void testHashCode() {
        RestaurantOperation otherOperation = new RestaurantOperation(id, restaurantId, dayOfWeek, openTime, closeTime, createdAt, updatedAt);
        assertEquals(restaurantOperation.hashCode(), otherOperation.hashCode());
    }

    @Test
    void getId() {
        assertEquals(id, restaurantOperation.getId());
    }

    @Test
    void getRestaurantId() {
        assertEquals(restaurantId, restaurantOperation.getRestaurantId());
    }

    @Test
    void getDayOfWeek() {
        assertEquals(dayOfWeek, restaurantOperation.getDayOfWeek());
    }

    @Test
    void getOpenTime() {
        assertEquals(openTime, restaurantOperation.getOpenTime());
    }

    @Test
    void getCloseTime() {
        assertEquals(closeTime, restaurantOperation.getCloseTime());
    }

    @Test
    void getCreatedAt() {
        assertEquals(createdAt, restaurantOperation.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(updatedAt, restaurantOperation.getUpdatedAt());
    }

    @Test
    void setId() {
        UUID newId = UUID.randomUUID();
        restaurantOperation.setId(newId);
        assertEquals(newId, restaurantOperation.getId());
    }

    @Test
    void setRestaurantId() {
        UUID newRestaurantId = UUID.randomUUID();
        restaurantOperation.setRestaurantId(newRestaurantId);
        assertEquals(newRestaurantId, restaurantOperation.getRestaurantId());
    }

    @Test
    void setDayOfWeek() {
        DayOfWeek newDayOfWeek = DayOfWeek.FRIDAY;
        restaurantOperation.setDayOfWeek(newDayOfWeek);
        assertEquals(newDayOfWeek, restaurantOperation.getDayOfWeek());
    }

    @Test
    void setOpenTime() {
        LocalTime newOpenTime = LocalTime.of(9, 0);
        restaurantOperation.setOpenTime(newOpenTime);
        assertEquals(newOpenTime, restaurantOperation.getOpenTime());
    }

    @Test
    void setCloseTime() {
        LocalTime newCloseTime = LocalTime.of(23, 0);
        restaurantOperation.setCloseTime(newCloseTime);
        assertEquals(newCloseTime, restaurantOperation.getCloseTime());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime newCreatedAt = LocalDateTime.now().minusDays(2);
        restaurantOperation.setCreatedAt(newCreatedAt);
        assertEquals(newCreatedAt, restaurantOperation.getCreatedAt());
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime newUpdatedAt = LocalDateTime.now().minusDays(2);
        restaurantOperation.setUpdatedAt(newUpdatedAt);
        assertEquals(newUpdatedAt, restaurantOperation.getUpdatedAt());
    }
}
