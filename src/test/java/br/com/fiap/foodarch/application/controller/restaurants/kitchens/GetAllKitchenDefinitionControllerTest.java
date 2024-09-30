package br.com.fiap.foodarch.application.controller.restaurants.kitchens;

import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;
import br.com.fiap.foodarch.domain.records.restaurants.kitchen.KitchenDefinitionOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.kitchen.GetAllKitchenDefinitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAllKitchenDefinitionControllerTest {

    @Mock
    private GetAllKitchenDefinitions getAllKitchenDefinitions;

    @InjectMocks
    private GetAllKitchenDefinitionController getAllKitchenDefinitionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllKitchenDefinitions() {
        // Cria uma lista simulada de definições de cozinha
        List<KitchensDefinition> simulatedKitchens = new ArrayList<>();
        simulatedKitchens.add(new KitchensDefinition(UUID.randomUUID(), "Italian", LocalDateTime.now(), LocalDateTime.now()));
        simulatedKitchens.add(new KitchensDefinition(UUID.randomUUID(), "Mexican", LocalDateTime.now(), LocalDateTime.now()));

        // Define o comportamento do mock
        when(getAllKitchenDefinitions.execute()).thenReturn(simulatedKitchens);

        // Executa o método do controlador
        ResponseEntity<List<KitchenDefinitionOutput>> response = getAllKitchenDefinitionController.getAllKitchenDefinitions();

        // Verifica se o método 'execute' foi chamado corretamente
        verify(getAllKitchenDefinitions, times(1)).execute();

        // Verifica se a resposta contém a lista correta
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());

        // Você pode adicionar mais verificações se necessário, por exemplo:
        assertEquals("Italian", response.getBody().get(0).name());
        assertEquals("Mexican", response.getBody().get(1).name());
    }
}
