package br.com.fiap.foodarch.domain.entities.restaurant;

import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantAppointmentTest {


    private RestaurantAppointment appointment;

    @BeforeEach
    void setUp() {
        // Inicializa uma nova instância de RestaurantAppointment para cada teste
        appointment = new RestaurantAppointment();
    }

    @Test
    void testOnCreate() {
        // Simula a criação de um novo objeto (como se fosse antes de persistir)
        appointment.onCreate();

        assertNotNull(appointment.getCreatedAt(), "CreatedAt não deve ser nulo após onCreate");
        assertNotNull(appointment.getUpdatedAt(), "UpdatedAt não deve ser nulo após onCreate");

        // Verifica se os timestamps estão corretos
        LocalDateTime now = LocalDateTime.now();
        assertTrue(appointment.getCreatedAt().isBefore(now.plusSeconds(1)) &&
                        appointment.getCreatedAt().isAfter(now.minusSeconds(1)),
                "CreatedAt deve estar próximo do horário atual");
        assertTrue(appointment.getUpdatedAt().isBefore(now.plusSeconds(1)) &&
                        appointment.getUpdatedAt().isAfter(now.minusSeconds(1)),
                "UpdatedAt deve estar próximo do horário atual");
    }

    @Test
    void testOnUpdate() {
        // Primeiro simula a criação inicial
        appointment.onCreate();

        // Guarda o valor original de createdAt para verificar que ele não muda
        LocalDateTime createdAtOriginal = appointment.getCreatedAt();

        // Simula uma atualização da entidade
        appointment.onUpdate();

        // Verifica que createdAt permanece o mesmo e updatedAt foi atualizado
        assertEquals(createdAtOriginal, appointment.getCreatedAt(), "CreatedAt não deve ser alterado no onUpdate");
        assertNotNull(appointment.getUpdatedAt(), "UpdatedAt não deve ser nulo após onUpdate");

        LocalDateTime now = LocalDateTime.now();
        assertTrue(appointment.getUpdatedAt().isBefore(now.plusSeconds(1)) &&
                        appointment.getUpdatedAt().isAfter(now.minusSeconds(1)),
                "UpdatedAt deve estar próximo do horário atual");
    }

    @Test
    void testGettersAndSetters() {
        // Prepara dados de teste
        UUID id = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        UUID tableId = UUID.randomUUID();
        LocalDateTime reservationDate = LocalDateTime.of(2024, 10, 10, 12, 30);
        LocalTime startTime = LocalTime.of(12, 30);
        LocalTime endTime = LocalTime.of(14, 30);

        // Define os valores nos setters
        appointment.setId(id);
        appointment.setRestaurantId(restaurantId);
        appointment.setTableId(tableId);
        appointment.setReservationDate(reservationDate);
        appointment.setStartTime(startTime);
        appointment.setEndTime(endTime);

        // Verifica se os getters retornam os valores corretos
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

        // Cria duas instâncias com o mesmo ID
        RestaurantAppointment appointment1 = new RestaurantAppointment();
        appointment1.setId(id1);

        RestaurantAppointment appointment2 = new RestaurantAppointment();
        appointment2.setId(id1);

        // Cria uma terceira instância com um ID diferente
        RestaurantAppointment appointment3 = new RestaurantAppointment();
        appointment3.setId(id2);

        // Verifica igualdade
        assertEquals(appointment1, appointment2, "Appointments com o mesmo id devem ser iguais");
        assertNotEquals(appointment1, appointment3, "Appointments com ids diferentes não devem ser iguais");

        // Verifica hashCode
        assertEquals(appointment1.hashCode(), appointment2.hashCode(), "Appointments com o mesmo id devem ter o mesmo hashCode");
        assertNotEquals(appointment1.hashCode(), appointment3.hashCode(), "Appointments com ids diferentes devem ter hashCodes diferentes");
    }

}
