package br.com.fiap.foodarch.infra.controller;

import br.com.fiap.foodarch.application.usecases.CreateUser;
import br.com.fiap.foodarch.application.usecases.ListUsers;
import br.com.fiap.foodarch.application.usecases.UpdateUser;
import br.com.fiap.foodarch.domain.entities.user.User;
import br.com.fiap.foodarch.infra.controller.dtos.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users",  description = "User management operations")
public class UserController {

  private final CreateUser createUser;
  public final ListUsers listUsers;
  private final UpdateUser updateUser;

  public UserController(
      CreateUser createUser,
      UpdateUser updateUser,
      ListUsers listUsers
  ) {
    this.createUser = createUser;
    this.updateUser = updateUser;
    this.listUsers = listUsers;
  }

  @PostMapping
  @Operation(summary = "Create user", description = "Create new user to FoodArch.")
  public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {

    User user = createUser.execute(userDTO);

    return ResponseEntity.status(201).body(user);
  }

  @GetMapping
  @Operation(summary = "List user's", description = "List all users from FoodArch.")
  public ResponseEntity<List<User>> getUsers() {

    return listUsers.execute().isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(listUsers.execute());
  }

  @PutMapping
  @Operation(summary = "Update user", description = "Update user from FoodArch.")
  public ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO userDTO) {

    User user = updateUser.execute(userDTO);

    return ResponseEntity.status(201).body(user);
  }

}
