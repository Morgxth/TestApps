package com.railways.testproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.railways.testproject.data.entity.ArrivalRecord;
import com.railways.testproject.data.entity.Station;
import com.railways.testproject.data.entity.Wagon;
import com.railways.testproject.service.WagonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WagonControllerTest {

    private WagonController wagonController;
    private WagonService wagonService;

    @BeforeEach
    public void setUp() {
        // Create a mock instance of WagonService
        wagonService = mock(WagonService.class);
        wagonController = new WagonController(wagonService);
    }

    @Test
    public void testGetWagonById() {
        // Create a sample Wagon
        Wagon wagon = new Wagon();
        wagon.setId(1L);
        wagon.setNumber("W123");

        // Set up the mock behavior for the service method
        when(wagonService.getWagonById(1L)).thenReturn(Optional.of(wagon));

        // Call the controller method and verify the response
        ResponseEntity<Wagon> response = wagonController.getWagonById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wagon, response.getBody());
    }

    @Test
    public void testGetWagonById_NotFound() {
        // Set up the mock behavior for the service method
        when(wagonService.getWagonById(2L)).thenReturn(Optional.empty());

        // Call the controller method and verify the response
        ResponseEntity<Wagon> response = wagonController.getWagonById(2L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllWagons() {
        // Create a list of sample Wagons
        List<Wagon> wagons = Arrays.asList(
                new Wagon(1L, "W123", new Station(), new ArrivalRecord()),
                new Wagon(2L, "W456", new Station(), new ArrivalRecord()),
                new Wagon(3L, "W789", new Station(), new ArrivalRecord())
        );

        // Set up the mock behavior for the service method
        when(wagonService.getAllWagons()).thenReturn(wagons);

        // Call the controller method and verify the response
        ResponseEntity<List<Wagon>> response = wagonController.getAllWagons();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wagons, response.getBody());
    }

    @Test
    public void testCreateWagon() {
        // Create a sample Wagon
        Wagon wagon = new Wagon();
        wagon.setNumber("W123");

        // Set up the mock behavior for the service method
        when(wagonService.createWagon(any(Wagon.class))).thenReturn(wagon);

        // Call the controller method and verify the response
        ResponseEntity<Wagon> response = wagonController.createWagon(wagon);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(wagon, response.getBody());
    }

    @Test
    public void testUpdateWagon() {
        // Create a sample Wagon
        Wagon existingWagon = new Wagon();
        existingWagon.setId(1L);
        existingWagon.setNumber("W123");

        // Create an updated Wagon
        Wagon updatedWagon = new Wagon();
        updatedWagon.setId(1L);
        updatedWagon.setNumber("W456");

        // Set up the mock behavior for the service method
        when(wagonService.updateWagon(eq(1L), any(Wagon.class))).thenReturn(updatedWagon);

        // Call the controller method and verify the response
        ResponseEntity<Wagon> response = wagonController.updateWagon(1L, updatedWagon);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedWagon, response.getBody());
    }

    @Test
    public void testUpdateWagon_NotFound() {
        // Create an updated Wagon
        Wagon updatedWagon = new Wagon();
        updatedWagon.setId(2L);
        updatedWagon.setNumber("W456");

        // Set up the mock behavior for the service method
        when(wagonService.updateWagon(eq(2L), any(Wagon.class))).thenReturn(null);

        // Call the controller method and verify the response
        ResponseEntity<Wagon> response = wagonController.updateWagon(2L, updatedWagon);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteWagon() {
        // Call the controller method and verify the response
        ResponseEntity<Void> response = wagonController.deleteWagon(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the service method was called with the correct ID
        verify(wagonService, times(1)).deleteWagonById(1L);
    }
}