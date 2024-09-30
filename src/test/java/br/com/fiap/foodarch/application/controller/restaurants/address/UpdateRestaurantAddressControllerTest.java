package br.com.fiap.foodarch.application.controller.restaurants.address;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAddressPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressInput;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.address.UpdateRestaurantAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UpdateRestaurantAddressControllerTest {

    @Mock
    private UpdateRestaurantAddress updateRestaurantAddress;

    @InjectMocks
    private UpdateRestaurantAddressController updateRestaurantAddressController;

    private UUID restaurantId;
    private UUID ownerId;
    private RestaurantAddressInput restaurantAddressInput;
    private RestaurantAddresses updatedRestaurantAddress;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        restaurantId = UUID.randomUUID();
        ownerId = UUID.randomUUID();
        restaurantAddressInput = new RestaurantAddressInput(
                "New Street",
                restaurantId,
                "456",
                "New Neighborhood",
                "New City",
                "New State",
                "New Complement",
                "New Country",
                "67890-123"
        );

        // Mockando o comportamento do serviço
        updatedRestaurantAddress = RestaurantAddresses.createRestaurantAddress()
                .restaurantId(restaurantId)
                .street("New Street")
                .number("456")
                .neighborhood("New Neighborhood")
                .city("New City")
                .state("New State")
                .complement("New Complement")
                .country("New Country")
                .zipCode("67890-123")
                .build();

        when(updateRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput)).thenReturn(updatedRestaurantAddress);
    }

    @Test
    void updateRestaurantAddress() {
        // Executa o método do controlador
        ResponseEntity<RestaurantAddressOutput> response = updateRestaurantAddressController.updateRestaurantAddress(restaurantId, ownerId, restaurantAddressInput);

        // Verifica o status HTTP
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Verifica se o corpo da resposta contém os dados esperados
        RestaurantAddressOutput expectedResponse = RestaurantAddressPresenter.restaurantAddressResponse(updatedRestaurantAddress);
        assertEquals(expectedResponse, response.getBody());

        // Verifica se o método 'execute' foi chamado corretamente
        verify(updateRestaurantAddress, times(1)).execute(restaurantId, ownerId, restaurantAddressInput);
    }
}
