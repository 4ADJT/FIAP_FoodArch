package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables;

import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RestaurantTablesRepositoryTest {

    @Mock
    private RestaurantTablesRepository restaurantTablesRepository;

    private UUID restaurantId;
    private UUID tableId;
    private RestaurantTables restaurantTable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
        restaurantId = UUID.randomUUID();
        tableId = UUID.randomUUID();
        restaurantTable = new RestaurantTables(tableId, restaurantId, 1, true);
    }

    @Test
    void createRestaurantTables() {
        when(restaurantTablesRepository.createRestaurantTables(restaurantId, restaurantTable))
                .thenReturn(restaurantTable);

        RestaurantTables createdTable = restaurantTablesRepository.createRestaurantTables(restaurantId, restaurantTable);

        assertNotNull(createdTable);
        assertEquals(restaurantTable, createdTable);
        verify(restaurantTablesRepository, times(1)).createRestaurantTables(restaurantId, restaurantTable);
    }

    @Test
    void findByRestaurantTableNumber() {
        int tableNumber = 1;

        when(restaurantTablesRepository.findByRestaurantTableNumber(tableNumber))
                .thenReturn(restaurantTable);

        RestaurantTables foundTable = restaurantTablesRepository.findByRestaurantTableNumber(tableNumber);

        assertNotNull(foundTable);
        assertEquals(tableNumber, foundTable.getTableNumber());
        verify(restaurantTablesRepository, times(1)).findByRestaurantTableNumber(tableNumber);
    }

}