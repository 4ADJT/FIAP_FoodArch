package br.com.fiap.foodarch.application.controller.users;

import br.com.fiap.foodarch.application.presenters.users.UserPresenter;
import br.com.fiap.foodarch.domain.records.users.UserOutput;
import br.com.fiap.foodarch.domain.usecases.users.ListUsers;
import br.com.fiap.foodarch.domain.entities.users.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

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
  public ResponseEntity<List<UserOutput>> getUsers() {

   if(listUsers.execute().isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    Stream<User> list = listUsers.execute().stream();

    List<UserOutput> response = list.map(UserPresenter::userResponse).toList();

    return ResponseEntity.ok(response);

  }

}
