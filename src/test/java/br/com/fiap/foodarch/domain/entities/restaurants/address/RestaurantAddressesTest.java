package br.com.fiap.foodarch.domain.entities.restaurants.address;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantAddressesTest {

    private RestaurantAddresses restaurantAddresses;
    private UUID id;
    private UUID restaurantId;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        restaurantId = UUID.randomUUID();
        now = LocalDateTime.now();

        restaurantAddresses = new RestaurantAddresses(
                id, restaurantId, "Main St", "123", "Downtown", "Cityville",
                "Stateville", "Apt 4B", "Countryland", "12345-678", now, now
        );
    }

    @Test
    void onCreate() {
        RestaurantAddresses newAddress = new RestaurantAddresses();
        newAddress.onCreate();

        assertNotNull(newAddress.getCreatedAt());
        assertNotNull(newAddress.getUpdatedAt());
    }

    @Test
    void onUpdate() {
        restaurantAddresses.onUpdate();
        assertEquals(now, restaurantAddresses.getCreatedAt());
        assertNotNull(restaurantAddresses.getUpdatedAt());
    }

    @Test
    void testEquals() {
        RestaurantAddresses otherAddress = new RestaurantAddresses(id, restaurantId, "Main St", "123", "Downtown", "Cityville", "Stateville", "Apt 4B", "Countryland", "12345-678", now);
        assertEquals(restaurantAddresses, otherAddress);
    }

    @Test
    void canEqual() {
        RestaurantAddresses otherAddress = new RestaurantAddresses();
        assertTrue(restaurantAddresses.canEqual(otherAddress));
    }

    @Test
    void testHashCode() {
        RestaurantAddresses otherAddress = new RestaurantAddresses(id, restaurantId, "Main St", "123", "Downtown", "Cityville", "Stateville", "Apt 4B", "Countryland", "12345-678", now);
        assertEquals(restaurantAddresses.hashCode(), otherAddress.hashCode());
    }

    @Test
    void getId() {
        assertEquals(id, restaurantAddresses.getId());
    }

    @Test
    void getRestaurantId() {
        assertEquals(restaurantId, restaurantAddresses.getRestaurantId());
    }

    @Test
    void getStreet() {
        assertEquals("Main St", restaurantAddresses.getStreet());
    }

    @Test
    void getNumber() {
        assertEquals("123", restaurantAddresses.getNumber());
    }

    @Test
    void getNeighborhood() {
        assertEquals("Downtown", restaurantAddresses.getNeighborhood());
    }

    @Test
    void getCity() {
        assertEquals("Cityville", restaurantAddresses.getCity());
    }

    @Test
    void getState() {
        assertEquals("Stateville", restaurantAddresses.getState());
    }

    @Test
    void getComplement() {
        assertEquals("Apt 4B", restaurantAddresses.getComplement());
    }

    @Test
    void getCountry() {
        assertEquals("Countryland", restaurantAddresses.getCountry());
    }

    @Test
    void getZipCode() {
        assertEquals("12345-678", restaurantAddresses.getZipCode());
    }

    @Test
    void getCreatedAt() {
        assertEquals(now, restaurantAddresses.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(now, restaurantAddresses.getUpdatedAt());
    }

    @Test
    void setId() {
        UUID newId = UUID.randomUUID();
        restaurantAddresses.setId(newId);
        assertEquals(newId, restaurantAddresses.getId());
    }

    @Test
    void setRestaurantId() {
        UUID newRestaurantId = UUID.randomUUID();
        restaurantAddresses.setRestaurantId(newRestaurantId);
        assertEquals(newRestaurantId, restaurantAddresses.getRestaurantId());
    }

    @Test
    void setStreet() {
        restaurantAddresses.setStreet("New St");
        assertEquals("New St", restaurantAddresses.getStreet());
    }

    @Test
    void setNumber() {
        restaurantAddresses.setNumber("456");
        assertEquals("456", restaurantAddresses.getNumber());
    }

    @Test
    void setNeighborhood() {
        restaurantAddresses.setNeighborhood("Uptown");
        assertEquals("Uptown", restaurantAddresses.getNeighborhood());
    }

    @Test
    void setCity() {
        restaurantAddresses.setCity("New City");
        assertEquals("New City", restaurantAddresses.getCity());
    }

    @Test
    void setState() {
        restaurantAddresses.setState("New State");
        assertEquals("New State", restaurantAddresses.getState());
    }

    @Test
    void setComplement() {
        restaurantAddresses.setComplement("Suite 1A");
        assertEquals("Suite 1A", restaurantAddresses.getComplement());
    }

    @Test
    void setCountry() {
        restaurantAddresses.setCountry("New Country");
        assertEquals("New Country", restaurantAddresses.getCountry());
    }

    @Test
    void setZipCode() {
        restaurantAddresses.setZipCode("98765-432");
        assertEquals("98765-432", restaurantAddresses.getZipCode());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime newTime = LocalDateTime.now().minusDays(1);
        restaurantAddresses.setCreatedAt(newTime);
        assertEquals(newTime, restaurantAddresses.getCreatedAt());
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime newTime = LocalDateTime.now().minusDays(1);
        restaurantAddresses.setUpdatedAt(newTime);
        assertEquals(newTime, restaurantAddresses.getUpdatedAt());
    }

    @Test
    void createRestaurantAddress() {
        RestaurantAddresses newAddress = RestaurantAddresses.createRestaurantAddress()
                .restaurantId(restaurantId)
                .street("Main St")
                .number("123")
                .neighborhood("Downtown")
                .city("Cityville")
                .state("Stateville")
                .complement("Apt 4B")
                .country("Countryland")
                .zipCode("12345-678")
                .build();

        assertEquals(restaurantId, newAddress.getRestaurantId());
        assertEquals("Main St", newAddress.getStreet());
    }

    @Test
    void updateRestaurantAddress() {
        RestaurantAddresses updatedAddress = RestaurantAddresses.updateRestaurantAddress()
                .id(id)
                .restaurantId(restaurantId)
                .street("New St")
                .number("456")
                .neighborhood("Uptown")
                .city("New City")
                .state("New State")
                .complement("Suite 1A")
                .country("New Country")
                .zipCode("98765-432")
                .createdAt(now)
                .build();

        assertEquals(id, updatedAddress.getId());
        assertEquals(restaurantId, updatedAddress.getRestaurantId());
        assertEquals("New St", updatedAddress.getStreet());
    }
}