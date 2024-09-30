package br.com.fiap.foodarch.domain.usecases.restaurants.address;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address.RestaurantAddressRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.entities.restaurants.address.UpdateRestaurantAddressFactory;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UpdateRestaurantAddressTest {

    @Mock
    private RestaurantAddressRepository repository;

    @Mock
    private UpdateRestaurantAddressFactory factory;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private UpdateRestaurantAddress updateRestaurantAddress;

    private UUID restaurantId;
    private UUID ownerId;
    private RestaurantAddressInput restaurantAddressInput;
    private RestaurantAddresses restaurantAddress;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantId = UUID.randomUUID();
        ownerId = UUID.randomUUID();
        restaurantAddressInput = new RestaurantAddressInput(
                "123 Main St",
                restaurantId,
                "1A",
                "Downtown",
                "Metropolis",
                "NY",
                "Apt 1",
                "USA",
                "12345"
        );
        restaurantAddress = new RestaurantAddresses(); // Assume um objeto válido
    }

    @Test
    void execute_shouldThrowRestaurantNotFound_whenAddressNotFound() {
        when(repository.findByRestaurantId(restaurantId)).thenReturn(null);

        assertThrows(RestaurantNotFound.class, () -> {
            updateRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput);
        });
    }

    @Test
    void execute_shouldThrowRestaurantNotFound_whenRestaurantNotFound() {
        when(repository.findByRestaurantId(restaurantId)).thenReturn(restaurantAddress);
        when(restaurantRepository.findById(restaurantId)).thenReturn(null);

        assertThrows(RestaurantNotFound.class, () -> {
            updateRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput);
        });
    }

    @Test
    void execute_shouldThrowRestaurantNotFound_whenRestaurantIdDoesNotMatch() {
        when(repository.findByRestaurantId(restaurantId)).thenReturn(restaurantAddress);
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(ownerId);
        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);

        restaurantAddressInput = new RestaurantAddressInput(
                "123 Main St",
                UUID.randomUUID(), // ID diferente
                "1A",
                "Downtown",
                "Metropolis",
                "NY",
                "Apt 1",
                "USA",
                "12345"
        );

        assertThrows(RestaurantNotFound.class, () -> {
            updateRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput);
        });
    }
}
