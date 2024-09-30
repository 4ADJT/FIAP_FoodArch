package br.com.fiap.foodarch.domain.usecases.restaurants.appointment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;

import java.util.UUID;

public class DeleteAppointment {
    private final RestaurantAppointmentRepository repository;

    public DeleteAppointment(RestaurantAppointmentRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID appointmentId) {
        repository.deleteById(appointmentId);
    }
}
