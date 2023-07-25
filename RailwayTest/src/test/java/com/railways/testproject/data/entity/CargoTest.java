package com.railways.testproject.data.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CargoTest {

    private Cargo cargo;

    @BeforeEach
    public void setUp() {
        // Create an instance of the Cargo class for testing
        cargo = new Cargo();
        cargo.setId(1L);
        cargo.setCode("C001");
        cargo.setName("Test Cargo");
    }

    @Test
    public void testGetId() {
        // Verify the getId() method returns the correct ID
        assertEquals(1L, cargo.getId());
    }

    @Test
    public void testGetCode() {
        // Verify the getCode() method returns the correct code
        assertEquals("C001", cargo.getCode());
    }

    @Test
    public void testGetName() {
        // Verify the getName() method returns the correct name
        assertEquals("Test Cargo", cargo.getName());
    }

    @Test
    public void testSetCode() {
        // Verify the setCode() method sets the code correctly
        cargo.setCode("C002");
        assertEquals("C002", cargo.getCode());
    }

    @Test
    public void testSetName() {
        // Verify the setName() method sets the name correctly
        cargo.setName("New Cargo Name");
        assertEquals("New Cargo Name", cargo.getName());
    }

    @Test
    public void testToString() {
        // Verify the toString() method returns the expected string representation
        String expectedString = "Cargo(id=1, code=C001, name=Test Cargo)";
        assertEquals(expectedString, cargo.toString());
    }
}