package br.com.fiap.foodarch.application.controller.restaurants.operatingHour;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourInput;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.CreateRestaurantOperatingHour;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CreateRestaurantOperatingHourControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateRestaurantOperatingHour createRestaurantOperatingHour;

    @InjectMocks
    private CreateRestaurantOperatingHourController createRestaurantOperatingHourController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(createRestaurantOperatingHourController).build();
        objectMapper = new ObjectMapper();  // Para serializar o objeto em JSON
    }

    @Test
    void createRestaurant() throws Exception {
        UUID restaurantId = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();

        // Criação do objeto de entrada
        RestaurantOperatingHourInput input = new RestaurantOperatingHourInput(
                restaurantId,
                DayOfWeek.MONDAY,
                "09:00:00",  // Hora de abertura
                "22:00:00"   // Hora de fechamento
        );

        // Criação do objeto de saída esperado
        RestaurantOperatingHours expectedOutput = new RestaurantOperatingHours(
                UUID.randomUUID(), // ID gerado
                restaurantId,
                DayOfWeek.MONDAY,
                "09:00:00",
                "22:00:00",
                null  // createdAt será gerado automaticamente
        );

        when(createRestaurantOperatingHour.execute(restaurantId, ownerId, input)).thenReturn(expectedOutput);

        mockMvc.perform(post("/restaurants/operatingHours/{restaurantId}", restaurantId)
                        .param("ownerId", ownerId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input))) // Serializa o objeto input para JSON
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(expectedOutput.getId().toString()))
                .andExpect(jsonPath("$.restaurantId").value(expectedOutput.getRestaurantId().toString()))
                .andExpect(jsonPath("$.dayOfWeek").value(expectedOutput.getDayOfWeek().toString()))
                .andExpect(jsonPath("$.openTime").value(expectedOutput.getOpenTime()))
                .andExpect(jsonPath("$.closeTime").value(expectedOutput.getCloseTime()));

        verify(createRestaurantOperatingHour, times(1)).execute(restaurantId, ownerId, input);
    }
}
