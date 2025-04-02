package org.springframework.samples.petclinic.vets.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpecialtyTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Specialty specialty = new Specialty();
        specialty.setId(1);
        specialty.setName("radiology");

        // Act & Assert
        assertEquals(1, specialty.getId());
        assertEquals("radiology", specialty.getName());
    }

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        Specialty specialty = new Specialty();
        
        // Assert
        assertNotNull(specialty);
        assertEquals(0, specialty.getId());
        assertNull(specialty.getName());
    }
    
    @Test
    void testToString() {
        // Arrange
        Specialty specialty = new Specialty();
        specialty.setId(1);
        specialty.setName("radiology");
        
        // Act
        String toString = specialty.toString();
        
        // Assert
        assertTrue(toString.contains("radiology"));
        assertTrue(toString.contains("1"));
    }
    
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Specialty specialty1 = new Specialty();
        specialty1.setId(1);
        specialty1.setName("radiology");
        
        Specialty specialty2 = new Specialty();
        specialty2.setId(1);
        specialty2.setName("different name but same id");
        
        Specialty specialty3 = new Specialty();
        specialty3.setId(2);
        specialty3.setName("radiology");
        
        // Act & Assert - equals tests
        assertEquals(specialty1, specialty1); // Same object
        assertEquals(specialty1, specialty2); // Same ID should be equal
        assertNotEquals(specialty1, specialty3); // Different ID should not be equal
        assertNotEquals(specialty1, null); // Null should not be equal
        assertNotEquals(specialty1, new Object()); // Different type should not be equal
        
        // Act & Assert - hashCode tests
        assertEquals(specialty1.hashCode(), specialty2.hashCode()); // Same ID should have same hashCode
        assertNotEquals(specialty1.hashCode(), specialty3.hashCode()); // Different ID should have different hashCode
    }
}