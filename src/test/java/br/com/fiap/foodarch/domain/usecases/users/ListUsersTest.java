package br.com.fiap.foodarch.domain.usecases.users;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListUsersTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private ListUsers listUsers;

    private Pageable pageable;
    private List<User> userList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pageable = Pageable.unpaged(); // or any specific pageable instance
        userList = new ArrayList<>();
    }

    @Test
    public void testExecuteSuccessfully() {
        // Arrange
        User user1 = new User(UUID.randomUUID(), "Alice", "alice@example.com", LocalDate.of(1992, 5, 15), "12345678901", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
        User user2 = new User(UUID.randomUUID(), "Bob", "bob@example.com", LocalDate.of(1990, 3, 10), "12345678902", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
        userList.add(user1);
        userList.add(user2);

        Page<User> userPage = new PageImpl<>(userList);
        when(repository.listUsers(pageable)).thenReturn(userPage);

        // Act
        Page<User> result = listUsers.execute(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals(user1.getName(), result.getContent().get(0).getName());
        assertEquals(user2.getName(), result.getContent().get(1).getName());
        verify(repository).listUsers(pageable);
    }

    @Test
    public void testExecuteEmptyList() {
        // Arrange
        Page<User> userPage = new PageImpl<>(userList);
        when(repository.listUsers(pageable)).thenReturn(userPage);

        // Act
        Page<User> result = listUsers.execute(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());
        verify(repository).listUsers(pageable);
    }
}
