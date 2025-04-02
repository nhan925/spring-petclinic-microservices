package org.springframework.samples.petclinic.vets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Vet}.
 */
public class VetTests {

    private Vet vet;
    
    private static final Integer TEST_ID = 1;
    private static final String TEST_FIRST_NAME = "James";
    private static final String TEST_LAST_NAME = "Carter";

    @BeforeEach
    void setUp() {
        vet = new Vet();
    }

    @Test
    void testGetAndSetId() {
        assertNull(vet.getId(), "Id should be null initially");
        
        vet.setId(TEST_ID);
        assertEquals(TEST_ID, vet.getId(), "Id should match the value we set");
    }

    @Test
    void testGetAndSetFirstName() {
        assertNull(vet.getFirstName(), "First name should be null initially");
        
        vet.setFirstName(TEST_FIRST_NAME);
        assertEquals(TEST_FIRST_NAME, vet.getFirstName(), "First name should match the value we set");
    }

    @Test
    void testGetAndSetLastName() {
        assertNull(vet.getLastName(), "Last name should be null initially");
        
        vet.setLastName(TEST_LAST_NAME);
        assertEquals(TEST_LAST_NAME, vet.getLastName(), "Last name should match the value we set");
    }

    @Test
    void testSpecialtiesInitiallyEmpty() {
        List<Specialty> specialties = vet.getSpecialties();
        assertNotNull(specialties, "Specialties list should not be null");
        assertTrue(specialties.isEmpty(), "Specialties list should be empty initially");
        assertEquals(0, vet.getNrOfSpecialties(), "Number of specialties should be 0 initially");
    }

    @Test
    void testAddSpecialty() {
        // Given a specialty
        Specialty specialty1 = createSpecialty(1, "Dentistry");
        
        // When adding the specialty to the vet
        vet.addSpecialty(specialty1);
        
        // Then the vet should have one specialty
        assertEquals(1, vet.getNrOfSpecialties(), "Vet should have one specialty");
        assertTrue(vet.getSpecialties().contains(specialty1), "Vet's specialties should contain the added specialty");
    }

    @Test
    void testAddMultipleSpecialties() {
        // Given three specialties
        Specialty specialty1 = createSpecialty(1, "Dentistry");
        Specialty specialty2 = createSpecialty(2, "Radiology");
        Specialty specialty3 = createSpecialty(3, "Surgery");
        
        // When adding the specialties to the vet
        vet.addSpecialty(specialty1);
        vet.addSpecialty(specialty2);
        vet.addSpecialty(specialty3);
        
        // Then the vet should have three specialties
        assertEquals(3, vet.getNrOfSpecialties(), "Vet should have three specialties");
        List<Specialty> specialties = vet.getSpecialties();
        assertTrue(specialties.contains(specialty1), "Specialties should contain specialty1");
        assertTrue(specialties.contains(specialty2), "Specialties should contain specialty2");
        assertTrue(specialties.contains(specialty3), "Specialties should contain specialty3");
    }

    @Test
    void testAddDuplicateSpecialty() {
        // Given a specialty
        Specialty specialty1 = createSpecialty(1, "Dentistry");
        
        // When adding the same specialty twice
        vet.addSpecialty(specialty1);
        vet.addSpecialty(specialty1);
        
        // Then the vet should have only one specialty (Set semantics)
        assertEquals(1, vet.getNrOfSpecialties(), "Vet should have only one specialty because of Set semantics");
    }

    @Test
    void testSpecialtiesAreSorted() {
        // Given three specialties with names in non-alphabetical order
        Specialty specialty1 = createSpecialty(1, "Radiology");
        Specialty specialty2 = createSpecialty(2, "Dentistry");
        Specialty specialty3 = createSpecialty(3, "Surgery");
        
        // When adding the specialties to the vet
        vet.addSpecialty(specialty1);
        vet.addSpecialty(specialty2);
        vet.addSpecialty(specialty3);
        
        // Then the specialties should be returned in alphabetical order
        List<Specialty> specialties = vet.getSpecialties();
        assertEquals(3, specialties.size(), "There should be 3 specialties");
        assertEquals("Dentistry", specialties.get(0).getName(), "First specialty should be Dentistry");
        assertEquals("Radiology", specialties.get(1).getName(), "Second specialty should be Radiology");
        assertEquals("Surgery", specialties.get(2).getName(), "Third specialty should be Surgery");
    }

    @Test
    void testSpecialtiesListIsUnmodifiable() {
        // Given a vet with a specialty
        Specialty specialty = createSpecialty(1, "Dentistry");
        vet.addSpecialty(specialty);
        
        // When trying to modify the returned list
        List<Specialty> specialties = vet.getSpecialties();
        
        // Then an exception should be thrown
        assertThrows(UnsupportedOperationException.class, () -> {
            specialties.add(createSpecialty(2, "Radiology"));
        }, "The returned specialties list should be unmodifiable");
    }

    /**
     * Helper method to create a specialty with the given id and name.
     */
    private Specialty createSpecialty(int id, String name) {
        Specialty specialty = new Specialty();
        try {
            // Use reflection to set the id since there's no setter for it
            java.lang.reflect.Field idField = Specialty.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(specialty, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set specialty ID: " + e.getMessage());
        }
        specialty.setName(name);
        return specialty;
    }
}