package br.com.fiap.foodarch.domain.entities.restaurants.operatingHour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantOperatingHoursTest {

    private RestaurantOperatingHours restaurantOperatingHours;
    private UUID id;
    private UUID restaurantId;
    private DayOfWeek dayOfWeek;
    private String openTime;
    private String closeTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        restaurantId = UUID.randomUUID();
        dayOfWeek = DayOfWeek.MONDAY;
        openTime = "08:00:00";
        closeTime = "22:00:00";
        createdAt = LocalDateTime.now().minusDays(1);
        updatedAt = LocalDateTime.now().minusDays(1);

        restaurantOperatingHours = new RestaurantOperatingHours(id, restaurantId, dayOfWeek, openTime, closeTime, createdAt, updatedAt);
    }

    @Test
    void onCreate() {
        RestaurantOperatingHours newOperatingHours = new RestaurantOperatingHours();
        newOperatingHours.onCreate();

        assertNotNull(newOperatingHours.getCreatedAt());
        assertNotNull(newOperatingHours.getUpdatedAt());
        assertEquals(newOperatingHours.getCreatedAt(), newOperatingHours.getUpdatedAt());
    }

    @Test
    void onUpdate() {
        restaurantOperatingHours.onUpdate();
        assertNotNull(restaurantOperatingHours.getUpdatedAt());
        assertNotEquals(restaurantOperatingHours.getCreatedAt(), restaurantOperatingHours.getUpdatedAt());
    }

    @Test
    void testEquals() {
        RestaurantOperatingHours otherOperatingHours = new RestaurantOperatingHours(id, restaurantId, dayOfWeek, openTime, closeTime, createdAt, updatedAt);
        assertEquals(restaurantOperatingHours, otherOperatingHours);
    }

    @Test
    void canEqual() {
        RestaurantOperatingHours otherOperatingHours = new RestaurantOperatingHours();
        assertTrue(restaurantOperatingHours.canEqual(otherOperatingHours));
    }

    @Test
    void testHashCode() {
        RestaurantOperatingHours otherOperatingHours = new RestaurantOperatingHours(id, restaurantId, dayOfWeek, openTime, closeTime, createdAt, updatedAt);
        assertEquals(restaurantOperatingHours.hashCode(), otherOperatingHours.hashCode());
    }

    @Test
    void getId() {
        assertEquals(id, restaurantOperatingHours.getId());
    }

    @Test
    void getRestaurantId() {
        assertEquals(restaurantId, restaurantOperatingHours.getRestaurantId());
    }

    @Test
    void getDayOfWeek() {
        assertEquals(dayOfWeek, restaurantOperatingHours.getDayOfWeek());
    }

    @Test
    void getOpenTime() {
        assertEquals(openTime, restaurantOperatingHours.getOpenTime());
    }

    @Test
    void getCloseTime() {
        assertEquals(closeTime, restaurantOperatingHours.getCloseTime());
    }

    @Test
    void getCreatedAt() {
        assertEquals(createdAt, restaurantOperatingHours.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(updatedAt, restaurantOperatingHours.getUpdatedAt());
    }

    @Test
    void setId() {
        UUID newId = UUID.randomUUID();
        restaurantOperatingHours.setId(newId);
        assertEquals(newId, restaurantOperatingHours.getId());
    }

    @Test
    void setRestaurantId() {
        UUID newRestaurantId = UUID.randomUUID();
        restaurantOperatingHours.setRestaurantId(newRestaurantId);
        assertEquals(newRestaurantId, restaurantOperatingHours.getRestaurantId());
    }

    @Test
    void setDayOfWeek() {
        DayOfWeek newDayOfWeek = DayOfWeek.FRIDAY;
        restaurantOperatingHours.setDayOfWeek(newDayOfWeek);
        assertEquals(newDayOfWeek, restaurantOperatingHours.getDayOfWeek());
    }

    @Test
    void setOpenTime() {
        String newOpenTime = "09:00:00";
        restaurantOperatingHours.setOpenTime(newOpenTime);
        assertEquals(newOpenTime, restaurantOperatingHours.getOpenTime());
    }

    @Test
    void setCloseTime() {
        String newCloseTime = "23:00:00";
        restaurantOperatingHours.setCloseTime(newCloseTime);
        assertEquals(newCloseTime, restaurantOperatingHours.getCloseTime());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime newCreatedAt = LocalDateTime.now().minusDays(2);
        restaurantOperatingHours.setCreatedAt(newCreatedAt);
        assertEquals(newCreatedAt, restaurantOperatingHours.getCreatedAt());
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime newUpdatedAt = LocalDateTime.now().minusDays(2);
        restaurantOperatingHours.setUpdatedAt(newUpdatedAt);
        assertEquals(newUpdatedAt, restaurantOperatingHours.getUpdatedAt());
    }

    @Test
    void testToString() {
        String expected = "RestaurantOperatingHours(id=" + id +
                ", restaurantId=" + restaurantId +
                ", dayOfWeek=" + dayOfWeek +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt + ")";
        assertEquals(expected, restaurantOperatingHours.toString());
    }

    @Test
    void createRestaurantOperatingHour() {
        RestaurantOperatingHours operatingHour = RestaurantOperatingHours.createRestaurantOperatingHour()
                .restaurantId(restaurantId)
                .dayOfWeek(dayOfWeek)
                .openTime(openTime)
                .closeTime(closeTime)
                .build();
        assertNotNull(operatingHour);
        assertEquals(restaurantId, operatingHour.getRestaurantId());
        assertEquals(dayOfWeek, operatingHour.getDayOfWeek());
        assertEquals(openTime, operatingHour.getOpenTime());
        assertEquals(closeTime, operatingHour.getCloseTime());
    }

    @Test
    void updateRestaurantOperatingHour() {
        RestaurantOperatingHours operatingHour = RestaurantOperatingHours.updateRestaurantOperatingHour()
                .id(id)
                .restaurantId(restaurantId)
                .dayOfWeek(dayOfWeek)
                .openTime(openTime)
                .closeTime(closeTime)
                .createdAt(createdAt)
                .build();
        assertNotNull(operatingHour);
        assertEquals(id, operatingHour.getId());
        assertEquals(restaurantId, operatingHour.getRestaurantId());
        assertEquals(dayOfWeek, operatingHour.getDayOfWeek());
        assertEquals(openTime, operatingHour.getOpenTime());
        assertEquals(closeTime, operatingHour.getCloseTime());
        assertEquals(createdAt, operatingHour.getCreatedAt());
    }
}