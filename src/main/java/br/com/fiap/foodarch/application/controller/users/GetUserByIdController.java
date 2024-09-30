package br.com.fiap.foodarch.application.controller.users;

import br.com.fiap.foodarch.application.presenters.users.UserPresenter;
import br.com.fiap.foodarch.domain.records.users.UserOutput;
import br.com.fiap.foodarch.domain.usecases.users.GetUserById;
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
@RequestMapping("/users")
@Tag(name = "Users")
public class GetUserByIdController {
    private final GetUserById getUserById;

    public GetUserByIdController(GetUserById getUserById) {
        this.getUserById = getUserById;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id.", description = "Get users by id from FoodArch.")
    public ResponseEntity<UserOutput> getUserById(@ParameterObject @PathVariable UUID id) {

        UserOutput user = UserPresenter.userResponse(getUserById.execute(id));

        return ResponseEntity.ok(user);
    }

}
