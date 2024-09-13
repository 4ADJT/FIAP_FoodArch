package br.com.fiap.foodarch.application.controller.users;

import br.com.fiap.foodarch.domain.usecases.users.CreateUser;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserInput;
import br.com.fiap.foodarch.domain.records.users.UserOutput;
import br.com.fiap.foodarch.application.presenters.users.UserPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class CreateUserController {
  private final CreateUser createUser;

  public CreateUserController(
      CreateUser createUser
  ) {
    this.createUser = createUser;
  }

  @PostMapping
  @Operation(summary = "Create user", description = "Create new user to FoodArch.")
  public ResponseEntity<UserOutput> createUser(@Valid @RequestBody UserInput userInput) {

    User user = createUser.execute(userInput);

    UserOutput response = UserPresenter.userResponse(user);

    return ResponseEntity.status(201).body(response);
  }

}
