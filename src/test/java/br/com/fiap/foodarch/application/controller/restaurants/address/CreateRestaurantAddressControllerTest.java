package br.com.fiap.foodarch.application.controller.restaurants.address;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAddressPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressInput;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.address.CreateRestaurantAddress;
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

class CreateRestaurantAddressControllerTest {

    @Mock
    private CreateRestaurantAddress createRestaurantAddress;

    @InjectMocks
    private CreateRestaurantAddressController createRestaurantAddressController;

    private UUID restaurantId;
    private UUID ownerId;
    private RestaurantAddressInput restaurantAddressInput;
    private RestaurantAddresses restaurantAddresses;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        restaurantId = UUID.randomUUID();
        ownerId = UUID.randomUUID();

        // Definindo o input do teste
        restaurantAddressInput = new RestaurantAddressInput(
                "Street 123",
                restaurantId,
                "123",
                "Downtown",
                "CityName",
                "StateName",
                "Apt 456",
                "CountryName",
                "12345-678"
        );

        // Definindo a saída esperada
        restaurantAddresses = RestaurantAddresses.createRestaurantAddress()
                .restaurantId(restaurantId)
                .street("Street 123")
                .number("123")
                .neighborhood("Downtown")
                .city("CityName")
                .state("StateName")
                .complement("Apt 456")
                .country("CountryName")
                .zipCode("12345-678")
                .build();

        // Mockando o comportamento do serviço
        when(createRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput))
                .thenReturn(restaurantAddresses);
    }

    @Test
    void createRestaurantAddress() {
        // Executa o método do controller
        ResponseEntity<RestaurantAddressOutput> response = createRestaurantAddressController.createRestaurantAddress(
                restaurantId, ownerId, restaurantAddressInput);

        // Verifica o status HTTP
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Verifica se o corpo da resposta contém os dados esperados
        RestaurantAddressOutput expectedResponse = RestaurantAddressPresenter.restaurantAddressResponse(restaurantAddresses);
        assertEquals(expectedResponse, response.getBody());

        // Verifica se o método 'execute' foi chamado corretamente
        verify(createRestaurantAddress, times(1)).execute(restaurantId, ownerId, restaurantAddressInput);
    }
}
