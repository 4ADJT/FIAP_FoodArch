package br.com.fiap.foodarch.domain.usecases.restaurants.kitchen;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.KitchenDefinitionRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.RestaurantKitchenRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.CreateRestaurantKitchenFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.RestaurantKitchens;
import br.com.fiap.foodarch.domain.exceptions.restaurants.KitchenAlreadyExistsException;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.kitchen.RestaurantKitchenOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaveRestaurantKitchenTest {

    @Mock
    private RestaurantKitchenRepository repository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CreateRestaurantKitchenFactory factory;

    @Mock
    private KitchenDefinitionRepository kitchenRepository;

    @InjectMocks
    private SaveRestaurantKitchen saveRestaurantKitchen;

    private UUID restaurantId;
    private UUID kitchenId;
    private UUID ownerId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantId = UUID.randomUUID();
        kitchenId = UUID.randomUUID();
        ownerId = UUID.randomUUID();
    }

    @Test
    void execute_shouldSaveKitchenSuccessfully() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(ownerId);

        RestaurantKitchens restaurantKitchens = new RestaurantKitchens();
        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);
        when(repository.getByRestaurantIdAndKitchenId(restaurantId, kitchenId)).thenReturn(Optional.empty());
        when(factory.createRestaurantKitchens(restaurantId, kitchenId)).thenReturn(restaurantKitchens);
        when(repository.save(restaurantKitchens)).thenReturn(restaurantKitchens);
        KitchensDefinition kitchen = new KitchensDefinition();
        when(kitchenRepository.getById(kitchenId)).thenReturn(kitchen);

        // When
        RestaurantKitchenOutput result = saveRestaurantKitchen.execute(restaurantId, kitchenId, ownerId);

        // Then
        assertNotNull(result);
        verify(repository).save(restaurantKitchens);
    }

    @Test
    void execute_shouldThrowUserUnauthorizedException_whenOwnerIsDifferent() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(UUID.randomUUID()); // Owner ID diferente do fornecido

        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);

        // When & Then
        UserUnauthorizedException exception = assertThrows(UserUnauthorizedException.class, () ->
                saveRestaurantKitchen.execute(restaurantId, kitchenId, ownerId));
        assertEquals("Unauthorized action to user", exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    void execute_shouldThrowKitchenAlreadyExistsException_whenKitchenExists() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(ownerId);
        RestaurantKitchens existingKitchen = new RestaurantKitchens();

        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);
        when(repository.getByRestaurantIdAndKitchenId(restaurantId, kitchenId)).thenReturn(Optional.of(existingKitchen));

        // When & Then
        KitchenAlreadyExistsException exception = assertThrows(KitchenAlreadyExistsException.class, () ->
                saveRestaurantKitchen.execute(restaurantId, kitchenId, ownerId));
        assertEquals("Kitchen already exists in restaurantId: " + existingKitchen.getRestaurantId(), exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
}
