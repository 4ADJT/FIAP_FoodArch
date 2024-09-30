package br.com.fiap.foodarch.domain.entities.restaurants.kitchens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class KitchensDefinitionTest {

    private KitchensDefinition kitchensDefinition;
    private UUID id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        name = "Italian Kitchen";
        createdAt = LocalDateTime.now().minusDays(1);
        updatedAt = LocalDateTime.now().minusDays(1);

        kitchensDefinition = new KitchensDefinition(id, name, createdAt, updatedAt);
    }

    @Test
    void testEquals() {
        KitchensDefinition otherKitchen = new KitchensDefinition(
                id, name, createdAt, updatedAt
        );
        assertEquals(kitchensDefinition, otherKitchen);
    }

    @Test
    void canEqual() {
        KitchensDefinition otherKitchen = new KitchensDefinition();
        assertTrue(kitchensDefinition.canEqual(otherKitchen));
    }

    @Test
    void testHashCode() {
        KitchensDefinition otherKitchen = new KitchensDefinition(
                id, name, createdAt, updatedAt
        );
        assertEquals(kitchensDefinition.hashCode(), otherKitchen.hashCode());
    }

    @Test
    void getId() {
        assertEquals(id, kitchensDefinition.getId());
    }

    @Test
    void getName() {
        assertEquals(name, kitchensDefinition.getName());
    }

    @Test
    void getCreatedAt() {
        assertEquals(createdAt, kitchensDefinition.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(updatedAt, kitchensDefinition.getUpdatedAt());
    }

    @Test
    void setId() {
        UUID newId = UUID.randomUUID();
        kitchensDefinition.setId(newId);
        assertEquals(newId, kitchensDefinition.getId());
    }

    @Test
    void setName() {
        String newName = "French Kitchen";
        kitchensDefinition.setName(newName);
        assertEquals(newName, kitchensDefinition.getName());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime newCreatedAt = LocalDateTime.now().minusDays(2);
        kitchensDefinition.setCreatedAt(newCreatedAt);
        assertEquals(newCreatedAt, kitchensDefinition.getCreatedAt());
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime newUpdatedAt = LocalDateTime.now().minusDays(2);
        kitchensDefinition.setUpdatedAt(newUpdatedAt);
        assertEquals(newUpdatedAt, kitchensDefinition.getUpdatedAt());
    }

}