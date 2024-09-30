package br.com.fiap.foodarch.application.controller.users;

import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserOutput;
import br.com.fiap.foodarch.domain.usecases.users.GetUserById;
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

public class GetUserByIdControllerTest {

    @Mock
    private GetUserById getUserById;

    @InjectMocks
    private GetUserByIdController getUserByIdController;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(UUID.randomUUID(), "John Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "123.456.789-09", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testGetUserByIdSuccessfully() {
        // Arrange
        UUID userId = user.getId();
        when(getUserById.execute(userId)).thenReturn(user);

        // Act
        ResponseEntity<UserOutput> response = getUserByIdController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user.getId(), response.getBody().id());
        assertEquals(user.getName(), response.getBody().name());
        verify(getUserById).execute(userId);
    }

    @Test
    public void testGetUserByIdThrowsException() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(getUserById.execute(userId)).thenThrow(new RuntimeException("Error fetching user"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getUserByIdController.getUserById(userId);
        });

        assertEquals("Error fetching user", exception.getMessage());
        verify(getUserById).execute(userId);
    }
}
