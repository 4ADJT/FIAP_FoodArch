package br.com.fiap.foodarch.domain.entities.restaurant;

import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {


    private RestaurantAppointment appointment;

    @BeforeEach
    void setUp() {
        appointment = new RestaurantAppointment();
    }

    @Test
    void testOnCreate() {
        // Simula a chamada do método onCreate (antes de persistir)
        appointment.onCreate();

        assertNotNull(appointment.getCreatedAt(), "CreatedAt não deve ser nulo após onCreate");
        assertNotNull(appointment.getUpdatedAt(), "UpdatedAt não deve ser nulo após onCreate");

        LocalDateTime now = LocalDateTime.now();
        assertTrue(appointment.getCreatedAt().isBefore(now.plusSeconds(1)) && appointment.getCreatedAt().isAfter(now.minusSeconds(1)),
                "CreatedAt deve estar próximo do horário atual");
        assertTrue(appointment.getUpdatedAt().isBefore(now.plusSeconds(1)) && appointment.getUpdatedAt().isAfter(now.minusSeconds(1)),
                "UpdatedAt deve estar próximo do horário atual");
    }

    @Test
    void testOnUpdate() {
        // Simula a criação inicial para garantir que os campos estão definidos
        appointment.onCreate();

        // Simula a atualização
        LocalDateTime createdAt = appointment.getCreatedAt();
        appointment.onUpdate();

        assertEquals(createdAt, appointment.getCreatedAt(), "CreatedAt não deve ser alterado no onUpdate");
        assertNotNull(appointment.getUpdatedAt(), "UpdatedAt não deve ser nulo após onUpdate");

        LocalDateTime now = LocalDateTime.now();
        assertTrue(appointment.getUpdatedAt().isBefore(now.plusSeconds(1)) && appointment.getUpdatedAt().isAfter(now.minusSeconds(1)),
                "UpdatedAt deve estar próximo do horário atual");
    }

    @Test
    void testGettersAndSetters() {
        UUID id = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        UUID tableId = UUID.randomUUID();
        LocalDateTime reservationDate = LocalDateTime.of(2024, 9, 29, 12, 0);
        LocalTime startTime = LocalTime.of(12, 0);
        LocalTime endTime = LocalTime.of(14, 0);

        appointment.setId(id);
        appointment.setRestaurantId(restaurantId);
        appointment.setTableId(tableId);
        appointment.setReservationDate(reservationDate);
        appointment.setStartTime(startTime);
        appointment.setEndTime(endTime);

        assertEquals(id, appointment.getId());
        assertEquals(restaurantId, appointment.getRestaurantId());
        assertEquals(tableId, appointment.getTableId());
        assertEquals(reservationDate, appointment.getReservationDate());
        assertEquals(startTime, appointment.getStartTime());
        assertEquals(endTime, appointment.getEndTime());
    }

    @Test
    void testEqualsAndHashCode() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        RestaurantAppointment appointment1 = new RestaurantAppointment();
        appointment1.setId(id1);

        RestaurantAppointment appointment2 = new RestaurantAppointment();
        appointment2.setId(id1);

        RestaurantAppointment appointment3 = new RestaurantAppointment();
        appointment3.setId(id2);

        assertEquals(appointment1, appointment2, "Appointments com o mesmo id devem ser iguais");
        assertNotEquals(appointment1, appointment3, "Appointments com ids diferentes não devem ser iguais");
        assertEquals(appointment1.hashCode(), appointment2.hashCode(), "Appointments com o mesmo id devem ter o mesmo hashCode");
        assertNotEquals(appointment1.hashCode(), appointment3.hashCode(), "Appointments com ids diferentes devem ter hashCodes diferentes");
    }

}
