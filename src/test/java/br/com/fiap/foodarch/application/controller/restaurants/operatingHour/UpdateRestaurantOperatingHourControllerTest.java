package br.com.fiap.foodarch.application.controller.restaurants.operatingHour;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourInput;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.UpdateRestaurantOperatingHour;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UpdateRestaurantOperatingHourControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UpdateRestaurantOperatingHour updateRestaurantOperatingHour;

    @InjectMocks
    private UpdateRestaurantOperatingHourController updateRestaurantOperatingHourController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(updateRestaurantOperatingHourController).build();
        objectMapper = new ObjectMapper(); // Para serializar objetos em JSON
    }

    @Test
    void updateRestaurantOperatingHour() throws Exception {
        UUID restaurantId = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();

        // Criação de um exemplo de entrada para o horário de funcionamento
        RestaurantOperatingHourInput input = new RestaurantOperatingHourInput(
                restaurantId,
                DayOfWeek.MONDAY,
                "09:00:00",
                "22:00:00"
        );

        // Criação de um exemplo de horário de operação atualizado
        RestaurantOperatingHours updatedOperatingHour = new RestaurantOperatingHours(
                UUID.randomUUID(),
                restaurantId,
                DayOfWeek.MONDAY,
                "09:00:00",
                "22:00:00",
                null
        );

        // Simula o retorno do método updateRestaurantOperatingHour.execute
        when(updateRestaurantOperatingHour.execute(restaurantId, ownerId, input)).thenReturn(updatedOperatingHour);

        // Executa a requisição e verifica a resposta
        mockMvc.perform(put("/restaurants/operatingHours/{restaurantId}", restaurantId)
                        .param("ownerId", ownerId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restaurantId").value(restaurantId.toString()))
                .andExpect(jsonPath("$.dayOfWeek").value(input.dayOfWeek().toString()))
                .andExpect(jsonPath("$.openTime").value(input.openTime()))
                .andExpect(jsonPath("$.closeTime").value(input.closeTime()));

        verify(updateRestaurantOperatingHour, times(1)).execute(restaurantId, ownerId, input);
    }
}
