package br.com.fiap.foodarch.domain.entities.restaurants.appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantAppointmentTest {

    private RestaurantAppointment appointment;
    private UUID id;
    private UUID restaurantId;
    private UUID tableId;
    private LocalDateTime reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        restaurantId = UUID.randomUUID();
        tableId = UUID.randomUUID();
        now = LocalDateTime.now();
        reservationDate = now.plusDays(1);
        startTime = LocalTime.of(12, 0);
        endTime = LocalTime.of(14, 0);

        appointment = new RestaurantAppointment(
                id, restaurantId, tableId, reservationDate, startTime, endTime, now, now
        );
    }

    @Test
    void onUpdate() {
        appointment.onUpdate();
        assertNotNull(appointment.getUpdatedAt());
    }

    @Test
    void testEquals() {
        RestaurantAppointment otherAppointment = new RestaurantAppointment(
                id, restaurantId, tableId, reservationDate, startTime, endTime, now, now
        );
        assertEquals(appointment, otherAppointment);
    }

    @Test
    void canEqual() {
        RestaurantAppointment otherAppointment = new RestaurantAppointment();
        assertTrue(appointment.canEqual(otherAppointment));
    }

    @Test
    void testHashCode() {
        RestaurantAppointment otherAppointment = new RestaurantAppointment(
                id, restaurantId, tableId, reservationDate, startTime, endTime, now, now
        );
        assertEquals(appointment.hashCode(), otherAppointment.hashCode());
    }

    @Test
    void getId() {
        assertEquals(id, appointment.getId());
    }

    @Test
    void getRestaurantId() {
        assertEquals(restaurantId, appointment.getRestaurantId());
    }

    @Test
    void getTableId() {
        assertEquals(tableId, appointment.getTableId());
    }

    @Test
    void getReservationDate() {
        assertEquals(reservationDate, appointment.getReservationDate());
    }

    @Test
    void getStartTime() {
        assertEquals(startTime, appointment.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals(endTime, appointment.getEndTime());
    }

    @Test
    void getCreatedAt() {
        assertEquals(now, appointment.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(now, appointment.getUpdatedAt());
    }

    @Test
    void setId() {
        UUID newId = UUID.randomUUID();
        appointment.setId(newId);
        assertEquals(newId, appointment.getId());
    }

    @Test
    void setRestaurantId() {
        UUID newRestaurantId = UUID.randomUUID();
        appointment.setRestaurantId(newRestaurantId);
        assertEquals(newRestaurantId, appointment.getRestaurantId());
    }

    @Test
    void setTableId() {
        UUID newTableId = UUID.randomUUID();
        appointment.setTableId(newTableId);
        assertEquals(newTableId, appointment.getTableId());
    }

    @Test
    void setReservationDate() {
        LocalDateTime newReservationDate = LocalDateTime.now().plusDays(2);
        appointment.setReservationDate(newReservationDate);
        assertEquals(newReservationDate, appointment.getReservationDate());
    }

    @Test
    void setStartTime() {
        LocalTime newStartTime = LocalTime.of(13, 0);
        appointment.setStartTime(newStartTime);
        assertEquals(newStartTime, appointment.getStartTime());
    }

    @Test
    void setEndTime() {
        LocalTime newEndTime = LocalTime.of(15, 0);
        appointment.setEndTime(newEndTime);
        assertEquals(newEndTime, appointment.getEndTime());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime newCreatedAt = LocalDateTime.now().minusDays(1);
        appointment.setCreatedAt(newCreatedAt);
        assertEquals(newCreatedAt, appointment.getCreatedAt());
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime newUpdatedAt = LocalDateTime.now().minusDays(1);
        appointment.setUpdatedAt(newUpdatedAt);
        assertEquals(newUpdatedAt, appointment.getUpdatedAt());
    }
}