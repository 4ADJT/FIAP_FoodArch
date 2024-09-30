package br.com.fiap.foodarch.application.controller.restaurants.operatingHour;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.GetRestaurantOperatingHourById;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GetRestaurantOperatingHourByIdControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GetRestaurantOperatingHourById getRestaurantById;

    @InjectMocks
    private GetRestaurantOperatingHourByIdController getRestaurantOperatingHourByIdController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(getRestaurantOperatingHourByIdController).build();
        objectMapper = new ObjectMapper();  // Para serializar objetos em JSON
    }

    @Test
    void getRestaurantAddressById() throws Exception {
        UUID restaurantId = UUID.randomUUID();

        // Criação de um exemplo de horário de operação
        RestaurantOperatingHours operatingHour1 = new RestaurantOperatingHours(
                UUID.randomUUID(),
                restaurantId,
                DayOfWeek.MONDAY,
                "09:00:00",
                "22:00:00",
                null
        );

        RestaurantOperatingHours operatingHour2 = new RestaurantOperatingHours(
                UUID.randomUUID(),
                restaurantId,
                DayOfWeek.TUESDAY,
                "09:00:00",
                "22:00:00",
                null
        );

        List<RestaurantOperatingHours> operatingHoursList = new ArrayList<>();
        operatingHoursList.add(operatingHour1);
        operatingHoursList.add(operatingHour2);

        // Simula o retorno do método getRestaurantById
        when(getRestaurantById.execute(restaurantId)).thenReturn(operatingHoursList);

        // Executa a requisição e verifica a resposta
        mockMvc.perform(get("/restaurants/operatingHours/{restaurantId}", restaurantId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].restaurantId").value(restaurantId.toString()))
                .andExpect(jsonPath("$[0].dayOfWeek").value(operatingHour1.getDayOfWeek().toString()))
                .andExpect(jsonPath("$[0].openTime").value(operatingHour1.getOpenTime()))
                .andExpect(jsonPath("$[0].closeTime").value(operatingHour1.getCloseTime()))
                .andExpect(jsonPath("$[1].restaurantId").value(restaurantId.toString()))
                .andExpect(jsonPath("$[1].dayOfWeek").value(operatingHour2.getDayOfWeek().toString()))
                .andExpect(jsonPath("$[1].openTime").value(operatingHour2.getOpenTime()))
                .andExpect(jsonPath("$[1].closeTime").value(operatingHour2.getCloseTime()));

        verify(getRestaurantById, times(1)).execute(restaurantId);
    }
}
