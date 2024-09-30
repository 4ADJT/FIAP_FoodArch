package br.com.fiap.foodarch.domain.usecases.restaurants.address;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address.RestaurantAddressRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantAddressNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetRestaurantByIdTest {

    @Mock
    private RestaurantAddressRepository repository;

    @InjectMocks
    private GetRestaurantById getRestaurantById;

    private UUID restaurantId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantId = UUID.randomUUID();
    }

    @Test
    void execute_shouldThrowIllegalArgumentException_whenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            getRestaurantById.execute(null);
        });
    }

    @Test
    void execute_shouldThrowRestaurantAddressNotFoundException_whenAddressNotFound() {
        when(repository.findByRestaurantId(restaurantId)).thenReturn(null);

        assertThrows(RestaurantAddressNotFoundException.class, () -> {
            getRestaurantById.execute(restaurantId);
        });
    }
}