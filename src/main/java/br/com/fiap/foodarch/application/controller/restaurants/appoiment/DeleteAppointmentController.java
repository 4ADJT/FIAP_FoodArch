package br.com.fiap.foodarch.application.controller.restaurants.appoiment;

import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.DeleteAppointment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/appointment")
@Tag(name = "Restaurants/Appointment")
public class DeleteAppointmentController {

    private DeleteAppointment deleteAppointment;

    public DeleteAppointmentController(DeleteAppointment deleteAppointment) {
        this.deleteAppointment = deleteAppointment;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete restaurant appointment by id", description = "Get restaurant appointment by id from FoodArch.")
    public ResponseEntity<HttpStatus> getRestaurantAddressById(
            @ParameterObject
            @PathVariable UUID id
    ) {
        deleteAppointment.execute(id);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
