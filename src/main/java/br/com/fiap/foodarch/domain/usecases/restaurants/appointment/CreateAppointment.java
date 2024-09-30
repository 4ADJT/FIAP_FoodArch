package br.com.fiap.foodarch.domain.usecases.restaurants.appointment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;

import java.util.UUID;

public class CreateAppointment {
    private final RestaurantAppointmentRepository repository;

    public CreateAppointment(RestaurantAppointmentRepository repository) {
        this.repository = repository;
    }

    public RestaurantAppointment execute(RestaurantAppointment appointment) {
        return repository.createAppointmentRestaurant(appointment);
    }
}
