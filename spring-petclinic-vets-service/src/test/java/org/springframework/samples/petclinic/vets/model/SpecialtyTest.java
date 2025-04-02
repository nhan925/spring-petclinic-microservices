package org.springframework.samples.petclinic.vets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Specialty}.
 */
public class SpecialtyTests {

    private Specialty specialty;
    private static final Integer TEST_ID = 1;
    private static final String TEST_NAME = "Dentistry";

    @BeforeEach
    void setUp() {
        specialty = new Specialty();
    }

    @Test
    void testGetId() {
        // Use reflection to set the id since there's no setter for it
        try {
            java.lang.reflect.Field idField = Specialty.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(specialty, TEST_ID);
            
            assertEquals(TEST_ID, specialty.getId(), "ID should match the value we set");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set ID field using reflection: " + e.getMessage());
        }
    }

    @Test
    void testGetAndSetName() {
        assertNull(specialty.getName(), "Name should be null initially");
        
        specialty.setName(TEST_NAME);
        assertEquals(TEST_NAME, specialty.getName(), "Name should match the value we set");
    }

    @Test
    void testSetNameWithNull() {
        specialty.setName(null);
        assertNull(specialty.getName(), "Name should accept null values");
    }

    @Test
    void testSetNameWithEmptyString() {
        specialty.setName("");
        assertEquals("", specialty.getName(), "Name should accept empty string");
    }
}