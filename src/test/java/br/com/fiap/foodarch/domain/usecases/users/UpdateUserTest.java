package br.com.fiap.foodarch.domain.usecases.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.UpdateUserFactory;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.users.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateUserTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UpdateUserFactory factory;

    @InjectMocks
    private UpdateUser updateUser;

    private UUID userId;
    private UserInput userInput;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        userInput = new UserInput("Alice", "alice@example.com", LocalDate.of(1992, 5, 15), "12345678901");
    }

    @Test
    public void testExecuteSuccessfully() {
        // Arrange
        User existingUser = new User(userId, "Alice", "alice@example.com", LocalDate.of(1992, 5, 15), "12345678901", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
        when(repository.findById(userId)).thenReturn(existingUser);

        List<User> userList = new ArrayList<>();
        userList.add(existingUser);
        when(repository.findByEmailOrCpf(userInput.email(), userInput.cpf())).thenReturn(userList);

        User updatedUser = new User(existingUser.getId(), "Alice Updated", "alice@example.com", LocalDate.of(1992, 5, 15), "12345678901", existingUser.getRestaurants(), existingUser.getCreatedAt(), LocalDateTime.now());
        when(factory.updateUser(existingUser.getId(), userInput.name(), existingUser.getEmail(), userInput.birthdate(), existingUser.getCpf(), existingUser.getCreatedAt())).thenReturn(updatedUser);
        when(repository.updateUser(updatedUser)).thenReturn(updatedUser);

        // Act
        User result = updateUser.execute(userId, userInput);

        // Assert
        assertNotNull(result);
        assertEquals("Alice Updated", result.getName());
        verify(repository).findById(userId);
        verify(repository).findByEmailOrCpf(userInput.email(), userInput.cpf());
        verify(repository).updateUser(updatedUser);
    }

    @Test
    public void testExecuteUserNotFound() {
        // Arrange
        when(repository.findById(userId)).thenReturn(null);

        List<User> userList = new ArrayList<>();
        when(repository.findByEmailOrCpf(userInput.email(), userInput.cpf())).thenReturn(userList);

        // Act & Assert
        UserUnauthorizedException exception = assertThrows(UserUnauthorizedException.class, () -> updateUser.execute(userId, userInput));
        assertEquals("User Unauthorized.", exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    public void testExecuteUnauthorizedUser() {
        // Arrange
        User existingUser = new User(userId, "Alice", "alice@example.com", LocalDate.of(1992, 5, 15), "12345678901", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
        when(repository.findById(userId)).thenReturn(existingUser);

        User unauthorizedUser = new User(UUID.randomUUID(), "Bob", "bob@example.com", LocalDate.of(1990, 3, 10), "12345678902", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
        List<User> userList = new ArrayList<>();
        userList.add(unauthorizedUser);
        when(repository.findByEmailOrCpf(userInput.email(), userInput.cpf())).thenReturn(userList);

        // Act & Assert
        UserUnauthorizedException exception = assertThrows(UserUnauthorizedException.class, () -> updateUser.execute(userId, userInput));
        assertEquals("User Unauthorized.", exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }
}
