package com.railways.testproject.data.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WagonTest {

    private Wagon wagon;

    @BeforeEach
    public void setUp() {
        wagon = new Wagon();
        wagon.setId(1L);
        wagon.setNumber("W123");
    }

    @Test
    public void testGetId() {
        assertEquals(1L, wagon.getId());
    }

    @Test
    public void testGetNumber() {
        assertEquals("W123", wagon.getNumber());
    }

    @Test
    public void testSetId() {
        wagon.setId(2L);
        assertEquals(2L, wagon.getId());
    }

    @Test
    public void testSetNumber() {
        wagon.setNumber("W456");
        assertEquals("W456", wagon.getNumber());
    }

    @Test
    public void testToString() {
        String expectedString = "Wagon(id=1, number=W123, currentStation=null, arrivalRecord=null)";
        assertEquals(expectedString, wagon.toString());
    }
}