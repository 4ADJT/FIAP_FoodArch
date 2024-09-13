package br.com.fiap.foodarch.infra.controller.users;

import br.com.fiap.foodarch.application.usecases.users.ListUsers;
import br.com.fiap.foodarch.domain.entities.users.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class GetAllUsersController {
  public final ListUsers listUsers;

  public GetAllUsersController(
      ListUsers listUsers
  ) {

    this.listUsers = listUsers;
  }

  @GetMapping
  @Operation(summary = "List user's", description = "List all users from FoodArch.")
  public ResponseEntity<List<User>> getUsers() {

    return listUsers.execute().isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(listUsers.execute());
  }

}
