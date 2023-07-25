package com.railways.testproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.railways.testproject.data.entity.Station;
import com.railways.testproject.service.StationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StationControllerTest {

    private StationController stationController;
    private StationService stationService;

    @BeforeEach
    public void setUp() {
        // Create a mock instance of StationService
        stationService = mock(StationService.class);
        stationController = new StationController(stationService);
    }

    @Test
    public void testGetStationById() {
        // Create a sample Station
        Station station = new Station();
        station.setId(1L);
        station.setName("Station A");

        // Set up the mock behavior for the service method
        when(stationService.getStationById(1L)).thenReturn(Optional.of(station));

        // Call the controller method and verify the response
        ResponseEntity<Station> response = stationController.getStationById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(station, response.getBody());
    }

    @Test
    public void testGetStationById_NotFound() {
        // Set up the mock behavior for the service method
        when(stationService.getStationById(2L)).thenReturn(Optional.empty());

        // Call the controller method and verify the response
        ResponseEntity<Station> response = stationController.getStationById(2L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllStations() {
        // Create a list of sample Stations
        List<Station> stations = Arrays.asList(
                new Station(1L, "Station A"),
                new Station(2L, "Station B"),
                new Station(3L, "Station C")
        );

        // Set up the mock behavior for the service method
        when(stationService.getAllStations()).thenReturn(stations);

        // Call the controller method and verify the response
        ResponseEntity<List<Station>> response = stationController.getAllStations();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stations, response.getBody());
    }

    @Test
    public void testCreateStation() {
        // Create a sample Station
        Station station = new Station();
        station.setName("Station A");

        // Set up the mock behavior for the service method
        when(stationService.createStation(any(Station.class))).thenReturn(station);

        // Call the controller method and verify the response
        ResponseEntity<Station> response = stationController.createStation(station);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(station, response.getBody());
    }

    @Test
    public void testUpdateStation() {
        // Create a sample Station
        Station existingStation = new Station();
        existingStation.setId(1L);
        existingStation.setName("Station A");

        // Create an updated Station
        Station updatedStation = new Station();
        updatedStation.setId(1L);
        updatedStation.setName("Updated Station A");

        // Set up the mock behavior for the service method
        when(stationService.updateStation(eq(1L), any(Station.class))).thenReturn(updatedStation);

        // Call the controller method and verify the response
        ResponseEntity<Station> response = stationController.updateStation(1L, updatedStation);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedStation, response.getBody());
    }

    @Test
    public void testUpdateStation_NotFound() {
        // Create an updated Station
        Station updatedStation = new Station();
        updatedStation.setId(2L);
        updatedStation.setName("Updated Station B");

        // Set up the mock behavior for the service method
        when(stationService.updateStation(eq(2L), any(Station.class))).thenReturn(null);

        // Call the controller method and verify the response
        ResponseEntity<Station> response = stationController.updateStation(2L, updatedStation);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteStation() {
        // Call the controller method and verify the response
        ResponseEntity<Boolean> response = stationController.deleteStation(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the service method was called with the correct ID
        verify(stationService, times(1)).deleteStationById(1L);
    }
}