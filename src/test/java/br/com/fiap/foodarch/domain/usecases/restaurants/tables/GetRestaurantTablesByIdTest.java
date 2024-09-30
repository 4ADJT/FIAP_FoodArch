package br.com.fiap.foodarch.domain.usecases.restaurants.tables;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables.RestaurantTablesRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantTablesNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetRestaurantTablesByIdTest {

    @Mock
    private RestaurantTablesRepository restaurantTablesRepository;

    @InjectMocks
    private GetRestaurantTablesById getRestaurantTablesById;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteSuccessfully() {
        // Arrange
        int tableNumber = 5;
        RestaurantTables tables = new RestaurantTables(UUID.randomUUID(), tableNumber, true);
        when(restaurantTablesRepository.findByRestaurantTableNumber(tableNumber)).thenReturn(tables);

        // Act
        RestaurantTables result = getRestaurantTablesById.execute(tableNumber);

        // Assert
        assertNotNull(result);
        assertEquals(tableNumber, result.getTableNumber());
        verify(restaurantTablesRepository).findByRestaurantTableNumber(tableNumber);
    }

    @Test
    public void testExecuteTableNumberNotFound() {
        // Arrange
        int tableNumber = 10;
        when(restaurantTablesRepository.findByRestaurantTableNumber(tableNumber)).thenReturn(null);

        // Act & Assert
        RestaurantTablesNotFoundException exception = assertThrows(RestaurantTablesNotFoundException.class, () -> {
            getRestaurantTablesById.execute(tableNumber);
        });
        assertEquals("Restaurant table number not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(restaurantTablesRepository).findByRestaurantTableNumber(tableNumber);
    }

    @Test
    public void testExecuteTableNumberNegative() {
        // Arrange
        int tableNumber = -1;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            getRestaurantTablesById.execute(tableNumber);
        });
        assertEquals("Table number cannot be null", exception.getMessage());
        // Não há necessidade de verificar o repositório, pois a exceção é lançada antes
    }
}
