package br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.UpdateRestaurantOperatingHourFactory;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.NothingToUpdate;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserNotExistsException;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateRestaurantOperatingHourTest {
    @Mock
    private RestaurantOperatingHourRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private UpdateRestaurantOperatingHourFactory factory;

    @InjectMocks
    private UpdateRestaurantOperatingHour updateRestaurantOperatingHour;

    private UUID restaurantId;
    private UUID ownerId;
    private RestaurantOperatingHourInput restaurantOperatingHourInput;
    private Restaurant restaurant;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantId = UUID.randomUUID();
        ownerId = UUID.randomUUID();
        restaurantOperatingHourInput = new RestaurantOperatingHourInput(
                restaurantId,  // restaurantId
                DayOfWeek.MONDAY,
                "09:00:00",
                "22:00:00"
        );

        user = new User();
        user.setId(ownerId);

        restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setOwnerId(ownerId);
    }

    @Test
    public void testUpdateOperatingHourSuccessfully() {
        List<RestaurantOperatingHours> operatingHoursList = new ArrayList<>();
        RestaurantOperatingHours existingOperatingHour = new RestaurantOperatingHours();
        existingOperatingHour.setDayOfWeek(DayOfWeek.MONDAY);
        existingOperatingHour.setRestaurantId(restaurantId);
        operatingHoursList.add(existingOperatingHour);

        when(repository.findByRestaurantId(restaurantId)).thenReturn(operatingHoursList);
        when(userRepository.findById(ownerId)).thenReturn(user);
        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);
        when(factory.updateRestaurantOperatingHour(any(), any(), any(), any(), any(), any()))
                .thenReturn(existingOperatingHour);
        when(repository.updateRestaurantOperatingHour(any(), any())).thenReturn(existingOperatingHour);

        RestaurantOperatingHours updatedOperatingHour = updateRestaurantOperatingHour.execute(restaurantId, ownerId, restaurantOperatingHourInput);

        assertNotNull(updatedOperatingHour);
        assertEquals(existingOperatingHour.getRestaurantId(), updatedOperatingHour.getRestaurantId());
        verify(repository).updateRestaurantOperatingHour(any(), any());
    }

    @Test
    public void testNothingToUpdate() {
        List<RestaurantOperatingHours> operatingHoursList = new ArrayList<>();
        RestaurantOperatingHours existingOperatingHour = new RestaurantOperatingHours();
        existingOperatingHour.setDayOfWeek(DayOfWeek.FRIDAY); // Different day of the week
        operatingHoursList.add(existingOperatingHour);

        when(repository.findByRestaurantId(restaurantId)).thenReturn(operatingHoursList);
        when(userRepository.findById(ownerId)).thenReturn(user);
        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);

        assertThrows(NothingToUpdate.class, () -> {
            updateRestaurantOperatingHour.execute(restaurantId, ownerId, restaurantOperatingHourInput);
        });
    }


    @Test
    public void testUserUnauthorized() {
        restaurant.setOwnerId(UUID.randomUUID()); // Set a different owner ID

        List<RestaurantOperatingHours> operatingHoursList = new ArrayList<>();
        RestaurantOperatingHours existingOperatingHour = new RestaurantOperatingHours();
        existingOperatingHour.setDayOfWeek(DayOfWeek.MONDAY);
        operatingHoursList.add(existingOperatingHour);

        when(repository.findByRestaurantId(restaurantId)).thenReturn(operatingHoursList);
        when(userRepository.findById(ownerId)).thenReturn(user);
        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);

        assertThrows(UserUnauthorizedException.class, () -> {
            updateRestaurantOperatingHour.execute(restaurantId, ownerId, restaurantOperatingHourInput);
        });
    }
}
