package br.com.fiap.foodarch.application.controller.restaurants.tables;

import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesInput;
import br.com.fiap.foodarch.domain.usecases.restaurants.tables.CreateRestaurantTables;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateRestaurantTablesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateRestaurantTables createRestaurantTables;

    @InjectMocks
    private CreateRestaurantTablesController createRestaurantTablesController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(createRestaurantTablesController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createRestaurantTables() throws Exception {
        // Dados simulados
        UUID ownerId = UUID.randomUUID();
        RestaurantTablesInput restaurantTablesInput = new RestaurantTablesInput(UUID.randomUUID(), ownerId, 10, true);
        RestaurantTables restaurantTables = new RestaurantTables(UUID.randomUUID(), ownerId, 1, true);

        // Configurando o mock
        when(createRestaurantTables.createTables(any(RestaurantTablesInput.class), eq(ownerId))).thenReturn(restaurantTables);

        // Fazendo a requisição POST
        mockMvc.perform(post("/restaurants/tables/{ownerId}", ownerId).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restaurantTablesInput))).andExpect(status().isCreated()) // Verifica se o status de resposta é 201
                .andExpect(jsonPath("$.id").value(restaurantTables.getId().toString())).andExpect(jsonPath("$.restaurantId").value(restaurantTables.getRestaurantId().toString())).andExpect(jsonPath("$.tableNumber").value(restaurantTables.getTableNumber())).andExpect(jsonPath("$.available").value(restaurantTables.isAvailable()));

        // Verifica se o método do serviço foi chamado uma vez
        verify(createRestaurantTables, times(1)).createTables(any(RestaurantTablesInput.class), eq(ownerId));
    }
}