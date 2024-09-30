package br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantOperatingHourNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class GetRestaurantOperatingHourByIdTest {

    @Mock
    private RestaurantOperatingHourRepository repository;

    @InjectMocks
    private GetRestaurantOperatingHourById getRestaurantOperatingHourById;

    private UUID restaurantId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantId = UUID.randomUUID();
    }

    @Test
    void execute_shouldThrowIllegalArgumentException_whenRestaurantIdIsNull() {
        // Given
        UUID nullRestaurantId = null;

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                getRestaurantOperatingHourById.execute(nullRestaurantId));
        assertEquals("Restaurant id is required", exception.getMessage());
    }

    @Test
    void execute_shouldThrowRestaurantOperatingHourNotFoundException_whenOperatingHourNotFound() {
        // Given
        when(repository.findByRestaurantId(restaurantId)).thenReturn(null);

        // When & Then
        RestaurantOperatingHourNotFoundException exception = assertThrows(RestaurantOperatingHourNotFoundException.class, () ->
                getRestaurantOperatingHourById.execute(restaurantId));
        assertEquals("Restaurant operating hour not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
}
