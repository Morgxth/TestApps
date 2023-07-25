package com.railways.testproject.data.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WagonPassportTest {

    private WagonPassport wagonPassport;

    @BeforeEach
    public void setUp() {
        // Create an instance of the WagonPassport class for testing
        wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);
        wagonPassport.setNumber("WP123");
        wagonPassport.setType("Freight");
        wagonPassport.setTareWeight(5000.0);
        wagonPassport.setCarryingCapacity(10000.0);
    }

    @Test
    public void testGetId() {
        assertEquals(1L, wagonPassport.getId());
    }

    @Test
    public void testGetNumber() {
        assertEquals("WP123", wagonPassport.getNumber());
    }

    @Test
    public void testGetType() {
        assertEquals("Freight", wagonPassport.getType());
    }

    @Test
    public void testGetTareWeight() {
        assertEquals(5000.0, wagonPassport.getTareWeight(), 0.001); // Delta for double comparison
    }

    @Test
    public void testGetCarryingCapacity() {
        assertEquals(10000.0, wagonPassport.getCarryingCapacity(), 0.001); // Delta for double comparison
    }

    @Test
    public void testSetId() {
        wagonPassport.setId(2L);
        assertEquals(2L, wagonPassport.getId());
    }

    @Test
    public void testSetNumber() {
        wagonPassport.setNumber("WP456");
        assertEquals("WP456", wagonPassport.getNumber());
    }

    @Test
    public void testSetType() {
        wagonPassport.setType("Passenger");
        assertEquals("Passenger", wagonPassport.getType());
    }

    @Test
    public void testSetTareWeight() {
        wagonPassport.setTareWeight(5500.0);
        assertEquals(5500.0, wagonPassport.getTareWeight(), 0.001); // Delta for double comparison
    }

    @Test
    public void testSetCarryingCapacity() {
        wagonPassport.setCarryingCapacity(12000.0);
        assertEquals(12000.0, wagonPassport.getCarryingCapacity(), 0.001); // Delta for double comparison
    }

    @Test
    public void testToString() {
        String expectedString = "WagonPassport(id=1, number=WP123, type=Freight, tareWeight=5000.0, carryingCapacity=10000.0)";
        assertEquals(expectedString, wagonPassport.toString());
    }
}