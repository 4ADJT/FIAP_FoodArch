package br.com.fiap.foodarch.application.controller.users;

import br.com.fiap.foodarch.application.presenters.users.UserPresenter;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserInput;
import br.com.fiap.foodarch.domain.records.users.UserOutput;
import br.com.fiap.foodarch.domain.usecases.users.UpdateUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UpdateUserController {

    private final UpdateUser updateUser;

    public UpdateUserController(UpdateUser updateUser) {
        this.updateUser = updateUser;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Update user from FoodArch.")
    public ResponseEntity<UserOutput> updateUser(@ParameterObject @PathVariable UUID id, @Valid @RequestBody UserInput userInput) {

        User user = updateUser.execute(id, userInput);

        UserOutput response = UserPresenter.userResponse(user);

        return ResponseEntity.status(201).body(response);
    }

}
