package br.com.fiap.foodarch.application.controller.users;

import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserInput;
import br.com.fiap.foodarch.domain.records.users.UserOutput;
import br.com.fiap.foodarch.domain.usecases.users.UpdateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateUserControllerTest {

    @Mock
    private UpdateUser updateUser;

    @InjectMocks
    private UpdateUserController updateUserController;

    private User user;
    private UserInput userInput;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(UUID.randomUUID(), "John Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "123.456.789-09", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
        userInput = new UserInput("Jane Doe", "jane.doe@example.com", LocalDate.of(1991, 2, 2), "987.654.321-00");
    }

    @Test
    public void testUpdateUserSuccessfully() {
        // Arrange
        UUID userId = user.getId();
        when(updateUser.execute(userId, userInput)).thenReturn(user);

        // Act
        ResponseEntity<UserOutput> response = updateUserController.updateUser(userId, userInput);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user.getId(), response.getBody().id());
        verify(updateUser).execute(userId, userInput);
    }

    @Test
    public void testUpdateUserThrowsException() {
        // Arrange
        UUID userId = user.getId();
        when(updateUser.execute(userId, userInput)).thenThrow(new RuntimeException("Error updating user"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            updateUserController.updateUser(userId, userInput);
        });

        assertEquals("Error updating user", exception.getMessage());
        verify(updateUser).execute(userId, userInput);
    }
}
