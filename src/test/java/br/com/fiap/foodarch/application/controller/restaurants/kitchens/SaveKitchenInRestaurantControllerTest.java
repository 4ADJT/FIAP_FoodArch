package br.com.fiap.foodarch.application.controller.restaurants.kitchens;

import br.com.fiap.foodarch.domain.records.restaurants.kitchen.RestaurantKitchenOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.kitchen.SaveRestaurantKitchen;
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

class SaveKitchenInRestaurantControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SaveRestaurantKitchen saveRestaurantKitchen;

    @InjectMocks
    private SaveKitchenInRestaurantController saveKitchenInRestaurantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(saveKitchenInRestaurantController).build();
    }

    @Test
    void saveKitchenInRestaurantController() throws Exception {
        UUID restaurantId = UUID.randomUUID();
        UUID kitchenId = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();
        RestaurantKitchenOutput expectedOutput = new RestaurantKitchenOutput(UUID.randomUUID(), null, null);

        when(saveRestaurantKitchen.execute(restaurantId, kitchenId, ownerId)).thenReturn(expectedOutput);

        mockMvc.perform(post("/restaurants/kitchen/{restaurantId}/{kitchenId}", restaurantId, kitchenId)
                        .param("ownerId", ownerId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(expectedOutput.id().toString()));

        verify(saveRestaurantKitchen, times(1)).execute(restaurantId, kitchenId, ownerId);
    }
}
