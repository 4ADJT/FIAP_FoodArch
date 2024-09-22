package br.com.fiap.foodarch.domain.usecases.restaurants.appointment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;

import java.util.UUID;

public class GetAppointmentById {
    private final RestaurantAppointmentRepository repository;

    public GetAppointmentById(RestaurantAppointmentRepository repository) {
        this.repository = repository;
    }

    public RestaurantAppointment execute(UUID appointmentId) {
        return repository.findByAppointmentId(appointmentId);
    }
}
