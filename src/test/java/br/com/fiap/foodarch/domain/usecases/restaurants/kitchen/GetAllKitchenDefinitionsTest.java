package br.com.fiap.foodarch.domain.usecases.restaurants.kitchen;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.KitchenDefinitionRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetAllKitchenDefinitionsTest {

    @Mock
    private KitchenDefinitionRepository repository;

    @InjectMocks
    private GetAllKitchenDefinitions getAllKitchenDefinitions;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldReturnListOfKitchenDefinitions() {
        // Given
        List<KitchensDefinition> expectedDefinitions = new ArrayList<>();
        expectedDefinitions.add(new KitchensDefinition()); // Adicione definições de cozinha conforme necessário
        expectedDefinitions.add(new KitchensDefinition());

        when(repository.getAll()).thenReturn(expectedDefinitions);

        // When
        List<KitchensDefinition> actualDefinitions = getAllKitchenDefinitions.execute();

        // Then
        assertNotNull(actualDefinitions);
        assertEquals(expectedDefinitions.size(), actualDefinitions.size());
        assertEquals(expectedDefinitions, actualDefinitions);
    }

    @Test
    void execute_shouldReturnEmptyList_whenNoKitchenDefinitionsExist() {
        // Given
        when(repository.getAll()).thenReturn(new ArrayList<>());

        // When
        List<KitchensDefinition> actualDefinitions = getAllKitchenDefinitions.execute();

        // Then
        assertNotNull(actualDefinitions);
        assertTrue(actualDefinitions.isEmpty());
    }
}
