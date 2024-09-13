package br.com.fiap.foodarch.infra.controller.users;

import br.com.fiap.foodarch.application.usecases.users.UpdateUser;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UpdateUserController {


  private final UpdateUser updateUser;

  public UpdateUserController(

      UpdateUser updateUser
  ) {

    this.updateUser = updateUser;

  }




  @PutMapping
  @Operation(summary = "Update user", description = "Update user from FoodArch.")
  public ResponseEntity<User> updateUser(@Valid @RequestBody UserInput userInput) {

    User user = updateUser.execute(userInput);

    return ResponseEntity.status(201).body(user);
  }

}
