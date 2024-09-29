package br.com.fiap.foodarch.application.controller.restaurants.tables;

import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.usecases.restaurants.tables.GetRestaurantTablesById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetRestauranttablesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GetRestaurantTablesById getRestaurantTablesById;

    @InjectMocks
    private GetRestauranttablesController getRestauranttablesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(getRestauranttablesController).build();
    }

    @Test
    void getRestaurantTableById() throws Exception {
        // Dados simulados
        int tableNumber = 1;
        UUID restaurantId = UUID.randomUUID();
        RestaurantTables restaurantTables = new RestaurantTables(UUID.randomUUID(), restaurantId, tableNumber, true);

        // Configura o mock
        when(getRestaurantTablesById.execute(tableNumber)).thenReturn(restaurantTables);

        // Faz a requisição GET
        mockMvc.perform(get("/restaurants/tables/{tableNumber}", tableNumber)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()); // Verifica se o status de resposta é 200

        // Verifica se o método do serviço foi chamado uma vez
        verify(getRestaurantTablesById, times(1)).execute(tableNumber);
    }
}