package br.com.fiap.foodarch.domain.usecases.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.CreateUserFactory;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserInput;
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

public class CreateUserTest {

    @Mock
    private UserRepository repository;

    @Mock
    private CreateUserFactory factory;

    @InjectMocks
    private CreateUser createUser;

    private UserInput userInput;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userInput = new UserInput("John Doe", "john@example.com", LocalDate.of(1990, 1, 1), "12345678909");
        user = new User(UUID.randomUUID(), userInput.name(), userInput.email(), userInput.birthdate(), userInput.cpf(), new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testExecuteSuccessfully() {
        // Arrange
        when(factory.createUser(userInput.name(), userInput.email(), userInput.birthdate(), userInput.cpf())).thenReturn(user);
        when(repository.createUser(user)).thenReturn(user);

        // Act
        User result = createUser.execute(userInput);

        // Assert
        assertNotNull(result);
        assertEquals(userInput.name(), result.getName());
        assertEquals(userInput.email(), result.getEmail());
        assertEquals(userInput.birthdate(), result.getBirthdate());
        assertEquals(userInput.cpf(), result.getCpf());
        verify(factory).createUser(userInput.name(), userInput.email(), userInput.birthdate(), userInput.cpf());
        verify(repository).createUser(user);
    }

    @Test
    public void testExecuteUserCreationFails() {
        // Arrange
        when(factory.createUser(userInput.name(), userInput.email(), userInput.birthdate(), userInput.cpf())).thenReturn(user);
        when(repository.createUser(user)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            createUser.execute(userInput);
        });
        assertEquals("Database error", exception.getMessage());
        verify(factory).createUser(userInput.name(), userInput.email(), userInput.birthdate(), userInput.cpf());
        verify(repository).createUser(user);
    }
}
