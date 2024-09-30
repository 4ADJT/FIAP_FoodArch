package br.com.fiap.foodarch.application.controller.users;

import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.users.UserOutput;
import br.com.fiap.foodarch.domain.usecases.users.ListUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllUsersControllerTest {

    @Mock
    private ListUsers listUsers;

    @InjectMocks
    private GetAllUsersController getAllUsersController;

    private List<User> userList;
    private Page<User> userPage;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userList = new ArrayList<>();
        userList.add(new User(UUID.randomUUID(), "John Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "123.456.789-09", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now()));
        userList.add(new User(UUID.randomUUID(), "Jane Doe", "jane.doe@example.com", LocalDate.of(1992, 5, 21), "987.654.321-00", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now()));
        userPage = new PageImpl<>(userList);
    }

    @Test
    public void testGetUsersSuccessfully() {
        // Arrange
        Pageable pageable = Pageable.ofSize(10);
        when(listUsers.execute(pageable)).thenReturn(userPage);

        // Act
        ResponseEntity<Page<UserOutput>> response = getAllUsersController.getUsers(pageable);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getContent().size());
        verify(listUsers).execute(pageable);
    }

    @Test
    public void testGetUsersEmptyList() {
        // Arrange
        Pageable pageable = Pageable.ofSize(10);
        when(listUsers.execute(pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        ResponseEntity<Page<UserOutput>> response = getAllUsersController.getUsers(pageable);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().isEmpty());
        verify(listUsers).execute(pageable);
    }

    @Test
    public void testGetUsersThrowsException() {
        // Arrange
        Pageable pageable = Pageable.ofSize(10);
        when(listUsers.execute(pageable)).thenThrow(new RuntimeException("Error fetching users"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getAllUsersController.getUsers(pageable);
        });

        assertEquals("Error fetching users", exception.getMessage());
        verify(listUsers).execute(pageable);
    }
}
