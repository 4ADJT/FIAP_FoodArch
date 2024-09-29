package br.com.fiap.foodarch.application.controller.restaurants.tables;

import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesInput;
import br.com.fiap.foodarch.domain.usecases.restaurants.tables.UpdateRestaurantTable;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UpdateRestaurantTableControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UpdateRestaurantTable updateRestaurantTable;

    @InjectMocks
    private UpdateRestaurantTableController updateRestaurantTableController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(updateRestaurantTableController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void updateRestaurantTable() throws Exception {
        // Dados simulados
        UUID restaurantId = UUID.randomUUID();
        UUID restaurantTableId = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();
        RestaurantTablesInput restaurantTablesInput = new RestaurantTablesInput(restaurantTableId, restaurantId, 10, true);
        RestaurantTables restaurantTables = new RestaurantTables(restaurantTableId, restaurantId, 10, true);

        // Configurando o mock
        when(updateRestaurantTable.execute(eq(restaurantId), any(RestaurantTablesInput.class), eq(ownerId))).thenReturn(restaurantTables);

        // Fazendo a requisição PUT
        mockMvc.perform(put("/restaurants/tables/{restaurantId}/{restaurantTableId}", restaurantId, restaurantTableId)
                .contentType(MediaType.APPLICATION_JSON).param("ownerId", ownerId.toString())
                .content(objectMapper.writeValueAsString(restaurantTablesInput))).andExpect(status().isOk());// Verifica se o status de resposta é 200

        // Verifica se o método do serviço foi chamado uma vez
        verify(updateRestaurantTable, times(1)).execute(eq(restaurantId), any(RestaurantTablesInput.class), eq(ownerId));
    }
}