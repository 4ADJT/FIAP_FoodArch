package br.com.fiap.foodarch.application.controller.restaurants.appoiment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAppointmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.domain.records.restaurants.appointment.RestaurantAppointmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.CreateAppointment;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.GetAppointmentById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/appointment")
@Tag(name = "Restaurants/Appointment")
public class CreateAppointmentController {

    private CreateAppointment createAppointment;

    public CreateAppointmentController(CreateAppointment createAppointment) {
        this.createAppointment = createAppointment;
    }

    @PostMapping("/create")
    @Operation(summary = "Create restaurant appointment", description = "Create restaurant appointment from FoodArch.")
    public ResponseEntity<RestaurantAppointmentOutput> getRestaurantAddressById(
            @ParameterObject
            @RequestBody RestaurantAppointment restaurantAppointment
    ) {
        RestaurantAppointment appointment = createAppointment.execute(restaurantAppointment);

        return ResponseEntity.ok(RestaurantAppointmentPresenter.restaurantAppointmentResponse(appointment));
    }

}
