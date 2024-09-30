package br.com.fiap.foodarch.application.controller.restaurants.address;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAddressPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.address.GetRestaurantById;
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

class GetRestaurantAddressByIdControllerTest {

    @Mock
    private GetRestaurantById getRestaurantById;

    @InjectMocks
    private GetRestaurantAddressByIdController getRestaurantAddressByIdController;

    private UUID restaurantId;
    private RestaurantAddresses restaurantAddresses;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        restaurantId = UUID.randomUUID();

        // Definindo a resposta esperada
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
        when(getRestaurantById.execute(restaurantId)).thenReturn(restaurantAddresses);
    }

    @Test
    void getRestaurantAddressById() {
        // Executa o método do controlador
        ResponseEntity<RestaurantAddressOutput> response = getRestaurantAddressByIdController.getRestaurantAddressById(restaurantId);

        // Verifica o status HTTP
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica se o corpo da resposta contém os dados esperados
        RestaurantAddressOutput expectedResponse = RestaurantAddressPresenter.restaurantAddressResponse(restaurantAddresses);
        assertEquals(expectedResponse, response.getBody());

        // Verifica se o método 'execute' foi chamado corretamente
        verify(getRestaurantById, times(1)).execute(restaurantId);
    }
}
