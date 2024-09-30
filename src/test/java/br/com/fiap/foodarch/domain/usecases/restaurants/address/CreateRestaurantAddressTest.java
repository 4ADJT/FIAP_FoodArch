package br.com.fiap.foodarch.domain.usecases.restaurants.address;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address.RestaurantAddressRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.address.CreateRestaurantAddressFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantAddressAlreadyExistsException;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CreateRestaurantAddressTest {

    @Mock
    private RestaurantAddressRepository repository;

    @Mock
    private CreateRestaurantAddressFactory factory;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private CreateRestaurantAddress createRestaurantAddress;

    private UUID restaurantId;
    private UUID ownerId;
    private RestaurantAddressInput restaurantAddressInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantId = UUID.randomUUID();
        ownerId = UUID.randomUUID();
        restaurantAddressInput = new RestaurantAddressInput("Main St", restaurantId, "123", "Downtown", "City", "State", "Apt 1", "Country", "12345");
    }

    @Test
    void execute_shouldThrowRestaurantNotFound_whenRestaurantDoesNotExist() {
        when(restaurantRepository.findById(restaurantId)).thenReturn(null);

        assertThrows(RestaurantNotFound.class, () -> {
            createRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput);
        });
    }

    @Test
    void execute_shouldThrowUserUnauthorized_whenOwnerIdDoesNotMatch() {
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", UUID.randomUUID(), null, null);
        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);

        assertThrows(UserUnauthorizedException.class, () -> {
            createRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput);
        });
    }

    @Test
    void execute_shouldThrowRestaurantAddressAlreadyExists_whenAddressExists() {
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", ownerId, null, null);
        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);
        when(repository.findByRestaurantId(restaurantId)).thenReturn(new RestaurantAddresses());

        assertThrows(RestaurantAddressAlreadyExistsException.class, () -> {
            createRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput);
        });
    }
}

