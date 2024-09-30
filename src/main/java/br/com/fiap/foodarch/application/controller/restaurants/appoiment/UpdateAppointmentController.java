package br.com.fiap.foodarch.application.controller.restaurants.appoiment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAppointmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.domain.records.restaurants.appointment.RestaurantAppointmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.GetAppointmentById;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.UpdateAppointment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/appointment")
@Tag(name = "Restaurants/Appointment")
public class UpdateAppointmentController {

    private UpdateAppointment updateAppointment;

    public UpdateAppointmentController(UpdateAppointment updateAppointment) {
        this.updateAppointment = updateAppointment;
    }

    @PostMapping("/update")
    @Operation(summary = "Update restaurant appointment", description = "Update restaurant appointment from FoodArch.")
    public ResponseEntity<RestaurantAppointmentOutput> updateRestaurantAppointment(
            @ParameterObject
            @RequestBody RestaurantAppointment restaurantAppointment
    ) {
        RestaurantAppointment appointment = updateAppointment.execute(restaurantAppointment);

        return ResponseEntity.ok(RestaurantAppointmentPresenter.restaurantAppointmentResponse(appointment));
    }

}
