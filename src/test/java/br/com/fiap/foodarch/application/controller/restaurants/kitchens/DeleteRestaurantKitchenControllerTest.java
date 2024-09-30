package br.com.fiap.foodarch.application.controller.restaurants.kitchens;

import br.com.fiap.foodarch.domain.usecases.restaurants.kitchen.DeleteRestaurantKitchen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteRestaurantKitchenControllerTest {

    @Mock
    private DeleteRestaurantKitchen deleteRestaurantKitchen;

    @InjectMocks
    private DeleteRestaurantKitchenController deleteRestaurantKitchenController;

    private UUID kitchenId;
    private UUID ownerId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        kitchenId = UUID.randomUUID();
        ownerId = UUID.randomUUID();
    }

    @Test
    void deleteRestaurantKitchenController() {
        // Executa o método do controlador
        ResponseEntity<Void> response = deleteRestaurantKitchenController.deleteRestaurantKitchenController(kitchenId, ownerId);

        // Verifica se o método 'execute' foi chamado corretamente
        verify(deleteRestaurantKitchen, times(1)).execute(kitchenId, ownerId);

        // Verifica se a resposta é 204 No Content
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
