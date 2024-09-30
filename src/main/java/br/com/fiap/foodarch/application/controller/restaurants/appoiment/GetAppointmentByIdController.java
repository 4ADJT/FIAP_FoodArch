package br.com.fiap.foodarch.application.controller.restaurants.appoiment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAppointmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.domain.records.restaurants.appointment.RestaurantAppointmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.GetAppointmentById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/appointment")
@Tag(name = "Restaurants/Appointment")
public class GetAppointmentByIdController {

    private GetAppointmentById getAppointmentById;

    public GetAppointmentByIdController(GetAppointmentById getAppointmentById) {
        this.getAppointmentById = getAppointmentById;
    }

    @GetMapping("/{appointmentId}")
    @Operation(summary = "Get restaurant appointment by id", description = "Get restaurant appointment by id from FoodArch.")
    public ResponseEntity<RestaurantAppointmentOutput> getRestaurantAddressById(
            @ParameterObject
            @PathVariable UUID restaurantId
    ) {
        RestaurantAppointment appointment = getAppointmentById.execute(restaurantId);

        return ResponseEntity.ok(RestaurantAppointmentPresenter.restaurantAppointmentResponse(appointment));
    }

}
