package com.railways.testproject.data.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StationTest {

    private Station station;

    @BeforeEach
    public void setUp() {
        // Create an instance of the Station class for testing
        station = new Station();
        station.setId(1L);
        station.setName("Test Station");
    }

    @Test
    public void testGetId() {
        // Verify the getId() method returns the correct ID
        assertEquals(1L, station.getId());
    }

    @Test
    public void testGetName() {
        // Verify the getName() method returns the correct name
        assertEquals("Test Station", station.getName());
    }

    @Test
    public void testSetId() {
        // Verify the setId() method sets the ID correctly
        station.setId(2L);
        assertEquals(2L, station.getId());
    }

    @Test
    public void testSetName() {
        // Verify the setName() method sets the name correctly
        station.setName("New Station Name");
        assertEquals("New Station Name", station.getName());
    }

    @Test
    public void testToString() {
        // Verify the toString() method returns the expected string representation
        String expectedString = "Station(id=1, name=Test Station)";
        assertEquals(expectedString, station.toString());
    }
}