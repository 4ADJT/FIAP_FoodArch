package br.com.fiap.foodarch.domain.entities.restaurants.kitchens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KitchensDefinitionTest {

    private KitchensDefinition kitchensDefinition;

    @BeforeEach
    public void setup() {
        kitchensDefinition = new KitchensDefinition();
        kitchensDefinition.setId(UUID.randomUUID());
        kitchensDefinition.setName("Italian Kitchen");
    }

    @Test
    public void testOnCreate() {
        // Act
        kitchensDefinition.onCreate();

        // Assert
        assertNotNull(kitchensDefinition.getCreatedAt());
        assertNotNull(kitchensDefinition.getUpdatedAt());
        assertTrue(kitchensDefinition.getCreatedAt().isEqual(kitchensDefinition.getUpdatedAt()));
    }

    @Test
    public void testOnUpdate() {
        // Arrange
        kitchensDefinition.onCreate(); // Simulate initial creation
        LocalDateTime originalCreatedAt = kitchensDefinition.getCreatedAt();

        // Act
        kitchensDefinition.onUpdate();

        // Assert
        assertNotNull(kitchensDefinition.getUpdatedAt());
    }
}
