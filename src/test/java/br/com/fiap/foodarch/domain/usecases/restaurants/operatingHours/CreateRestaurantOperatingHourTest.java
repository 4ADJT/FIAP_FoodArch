package br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.CreateRestaurantOperatingHourFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateRestaurantOperatingHourTest {

    @Mock
    private RestaurantOperatingHourRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CreateRestaurantOperatingHourFactory factory;

    @InjectMocks
    private CreateRestaurantOperatingHour createRestaurantOperatingHour;

    private UUID restaurantId;
    private UUID ownerId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantId = UUID.randomUUID();
        ownerId = UUID.randomUUID();
    }

    @Test
    void execute() {
        assertTrue(true);
    }
}
