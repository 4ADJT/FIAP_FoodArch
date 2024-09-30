package br.com.fiap.foodarch.application.controller.users;

import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserInput;
import br.com.fiap.foodarch.domain.records.users.UserOutput;
import br.com.fiap.foodarch.domain.usecases.users.CreateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateUserControllerTest {

    @Mock
    private CreateUser createUser;

    @InjectMocks
    private CreateUserController createUserController;

    private UserInput userInput;
    private User createdUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userInput = new UserInput("John Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "123.456.789-09");
        createdUser = new User(UUID.randomUUID(), "John Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "123.456.789-09", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testCreateUserSuccessfully() {
        // Arrange
        when(createUser.execute(userInput)).thenReturn(createdUser);

        // Act
        ResponseEntity<UserOutput> response = createUserController.createUser(userInput);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdUser.getId(), response.getBody().id());
        assertEquals(createdUser.getName(), response.getBody().name());
        verify(createUser).execute(userInput);
    }

}
