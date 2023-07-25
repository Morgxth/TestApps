package com.railways.testproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.railways.testproject.data.entity.WagonPassport;
import com.railways.testproject.service.WagonPassportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WagonPassportControllerTest {

    private WagonPassportController wagonPassportController;
    private WagonPassportService wagonPassportService;

    @BeforeEach
    public void setUp() {
        // Create a mock instance of WagonPassportService
        wagonPassportService = mock(WagonPassportService.class);
        wagonPassportController = new WagonPassportController(wagonPassportService);
    }

    @Test
    public void testCreateWagonPassport() {
        // Create a sample WagonPassport
        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);
        wagonPassport.setNumber("WP001");

        // Set up the mock behavior for the service method
        when(wagonPassportService.createWagonPassport(any(WagonPassport.class))).thenReturn(wagonPassport);

        // Call the controller method and verify the response
        ResponseEntity<WagonPassport> response = wagonPassportController.createWagonPassport(wagonPassport);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(wagonPassport, response.getBody());
    }

    @Test
    public void testGetAllWagonPassports() {
        // Create a list of sample WagonPassports
        List<WagonPassport> wagonPassports = Arrays.asList(
                new WagonPassport(1L, "WP001", "Type A", 1000.0, 5000.0),
                new WagonPassport(2L, "WP002", "Type B", 1200.0, 6000.0),
                new WagonPassport(3L, "WP003", "Type C", 1500.0, 7000.0)
        );

        // Set up the mock behavior for the service method
        when(wagonPassportService.getAllWagonPassports()).thenReturn(wagonPassports);

        // Call the controller method and verify the response
        ResponseEntity<List<WagonPassport>> response = wagonPassportController.getAllWagonPassports();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wagonPassports, response.getBody());
    }

    @Test
    public void testGetWagonPassportById() {
        // Create a sample WagonPassport
        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);
        wagonPassport.setNumber("WP001");

        // Set up the mock behavior for the service method
        when(wagonPassportService.getWagonPassportById(1L)).thenReturn(wagonPassport);

        // Call the controller method and verify the response
        ResponseEntity<WagonPassport> response = wagonPassportController.getWagonPassportById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wagonPassport, response.getBody());
    }

    @Test
    public void testGetWagonPassportById_NotFound() {
        // Set up the mock behavior for the service method
        when(wagonPassportService.getWagonPassportById(2L)).thenReturn(null);

        // Call the controller method and verify the response
        ResponseEntity<WagonPassport> response = wagonPassportController.getWagonPassportById(2L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateWagonPassport() {
        // Create a sample WagonPassport
        WagonPassport existingWagonPassport = new WagonPassport();
        existingWagonPassport.setId(1L);
        existingWagonPassport.setNumber("WP001");

        // Create an updated WagonPassport
        WagonPassport updatedWagonPassport = new WagonPassport();
        updatedWagonPassport.setId(1L);
        updatedWagonPassport.setNumber("WP001");
        updatedWagonPassport.setType("Type X");
        updatedWagonPassport.setTareWeight(1100.0);
        updatedWagonPassport.setCarryingCapacity(5500.0);

        // Set up the mock behavior for the service method
        when(wagonPassportService.updateWagonPassport(eq(1L), any(WagonPassport.class))).thenReturn(updatedWagonPassport);

        // Call the controller method and verify the response
        ResponseEntity<WagonPassport> response = wagonPassportController.updateWagonPassport(1L, updatedWagonPassport);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedWagonPassport, response.getBody());
    }

    @Test
    public void testUpdateWagonPassport_NotFound() {
        // Create an updated WagonPassport
        WagonPassport updatedWagonPassport = new WagonPassport();
        updatedWagonPassport.setId(2L);
        updatedWagonPassport.setNumber("WP002");
        updatedWagonPassport.setType("Type Y");
        updatedWagonPassport.setTareWeight(1300.0);
        updatedWagonPassport.setCarryingCapacity(6500.0);

        // Set up the mock behavior for the service method
        when(wagonPassportService.updateWagonPassport(eq(2L), any(WagonPassport.class))).thenReturn(null);

        // Call the controller method and verify the response
        ResponseEntity<WagonPassport> response = wagonPassportController.updateWagonPassport(2L, updatedWagonPassport);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteWagonPassport() {
        // Set up the mock behavior for the service method
        when(wagonPassportService.deleteWagonPassport(1L)).thenReturn(true);

        // Call the controller method and verify the response
        ResponseEntity<Boolean> response = wagonPassportController.deleteWagonPassport(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    public void testDeleteWagonPassport_NotFound() {
        // Set up the mock behavior for the service method
        when(wagonPassportService.deleteWagonPassport(2L)).thenReturn(false);

        // Call the controller method and verify the response
        ResponseEntity<Boolean> response = wagonPassportController.deleteWagonPassport(2L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.getBody());
    }
}