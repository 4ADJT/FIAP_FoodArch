package br.com.fiap.foodarch.domain.usecases.restaurants;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.CreateRestaurantFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.users.UserNotExistsException;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class CreateRestaurantTest {

    @Mock
    private RestaurantRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CreateRestaurantFactory factory;

    @InjectMocks
    private CreateRestaurant createRestaurant;

    private RestaurantInput restaurantInput;
    private UUID ownerId;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerId = UUID.randomUUID();
        restaurantInput = new RestaurantInput("Test Restaurant", ownerId);
        user = new User();
        user.setId(ownerId);
    }

    @Test
    public void testCreateRestaurantSuccessfully() {
        when(userRepository.findById(ownerId)).thenReturn(user);
        when(factory.createRestaurant(any(), any())).thenReturn(new Restaurant());
        when(repository.createRestaurant(any(), any())).thenReturn(new Restaurant());

        Restaurant createdRestaurant = createRestaurant.execute(restaurantInput, ownerId);

        assertNotNull(createdRestaurant);
        verify(repository).createRestaurant(any(), eq(ownerId));
    }

}
