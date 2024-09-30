package br.com.fiap.foodarch.domain.usecases.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetUserByIdTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private GetUserById getUserById;

    private UUID userId;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        user = new User(userId, "John Doe", "john@example.com", LocalDate.of(1990, 1, 1), "12345678909", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testExecuteSuccessfully() {
        // Arrange
        when(repository.findById(userId)).thenReturn(user);

        // Act
        User result = getUserById.execute(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());
        verify(repository).findById(userId);
    }

    @Test
    public void testExecuteUserNotFound() {
        // Arrange
        when(repository.findById(userId)).thenReturn(null);

        // Act
        User result = getUserById.execute(userId);

        // Assert
        assertNull(result);
        verify(repository).findById(userId);
    }
}
