package br.com.fiap.foodarch.application.controller.users;

import br.com.fiap.foodarch.application.presenters.users.UserPresenter;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserOutput;
import br.com.fiap.foodarch.domain.usecases.users.ListUsers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class GetAllUsersController {
    public final ListUsers listUsers;

    public GetAllUsersController(ListUsers listUsers) {

        this.listUsers = listUsers;
    }

    @GetMapping
    @Operation(summary = "List user's", description = "List all users from FoodArch.")
    public ResponseEntity<Page<UserOutput>> getUsers(@ParameterObject @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<User> list = listUsers.execute(pageable);

        Page<UserOutput> output = list.map(UserPresenter::userResponse);

        return ResponseEntity.ok(output);

    }

}
