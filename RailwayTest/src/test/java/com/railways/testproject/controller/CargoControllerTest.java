package com.railways.testproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.railways.testproject.data.entity.Cargo;
import com.railways.testproject.service.CargoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CargoControllerTest {

    private CargoController cargoController;
    private CargoService cargoService;

    @BeforeEach
    public void setUp() {
        // Create a mock instance of CargoService
        cargoService = mock(CargoService.class);
        cargoController = new CargoController(cargoService);
    }

    @Test
    public void testCreateCargo() {
        // Create a sample Cargo
        Cargo cargo = new Cargo();
        cargo.setId(1L);
        cargo.setCode("C001");
        cargo.setName("Cargo 1");

        // Set up the mock behavior for the service method
        when(cargoService.createCargo(any(Cargo.class))).thenReturn(cargo);

        // Call the controller method and verify the response
        ResponseEntity<Cargo> response = cargoController.createCargo(cargo);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cargo, response.getBody());
    }

    @Test
    public void testGetAllCargo() {
        // Create a list of sample Cargo
        List<Cargo> cargoList = Arrays.asList(
                new Cargo(1L, "C001", "Cargo 1"),
                new Cargo(2L, "C002", "Cargo 2"),
                new Cargo(3L, "C003", "Cargo 3")
        );

        // Set up the mock behavior for the service method
        when(cargoService.getAllCargo()).thenReturn(cargoList);

        // Call the controller method and verify the response
        ResponseEntity<List<Cargo>> response = cargoController.getAllCargo();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cargoList, response.getBody());
    }

    @Test
    public void testGetCargoById() {
        // Create a sample Cargo
        Cargo cargo = new Cargo();
        cargo.setId(1L);
        cargo.setCode("C001");
        cargo.setName("Cargo 1");

        // Set up the mock behavior for the service method
        when(cargoService.getCargoById(1L)).thenReturn(cargo);

        // Call the controller method and verify the response
        ResponseEntity<Cargo> response = cargoController.getCargoById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cargo, response.getBody());
    }

    @Test
    public void testGetCargoById_NotFound() {
        // Set up the mock behavior for the service method
        when(cargoService.getCargoById(2L)).thenReturn(null);

        // Call the controller method and verify the response
        ResponseEntity<Cargo> response = cargoController.getCargoById(2L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateCargo() {
        // Create a sample Cargo
        Cargo existingCargo = new Cargo();
        existingCargo.setId(1L);
        existingCargo.setCode("C001");
        existingCargo.setName("Cargo 1");

        // Create an updated Cargo
        Cargo updatedCargo = new Cargo();
        updatedCargo.setId(1L);
        updatedCargo.setCode("C001");
        updatedCargo.setName("Updated Cargo 1");

        // Set up the mock behavior for the service method
        when(cargoService.updateCargo(eq(1L), any(Cargo.class))).thenReturn(updatedCargo);

        // Call the controller method and verify the response
        ResponseEntity<Cargo> response = cargoController.updateCargo(1L, updatedCargo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCargo, response.getBody());
    }

    @Test
    public void testUpdateCargo_NotFound() {
        // Create an updated Cargo
        Cargo updatedCargo = new Cargo();
        updatedCargo.setId(2L);
        updatedCargo.setCode("C002");
        updatedCargo.setName("Updated Cargo 2");

        // Set up the mock behavior for the service method
        when(cargoService.updateCargo(eq(2L), any(Cargo.class))).thenReturn(null);

        // Call the controller method and verify the response
        ResponseEntity<Cargo> response = cargoController.updateCargo(2L, updatedCargo);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteCargo() {
        // Set up the mock behavior for the service method
        when(cargoService.deleteCargo(1L)).thenReturn(true);

        // Call the controller method and verify the response
        ResponseEntity<Boolean> response = cargoController.deleteCargo(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    public void testDeleteCargo_NotFound() {
        // Set up the mock behavior for the service method
        when(cargoService.deleteCargo(2L)).thenReturn(false);

        // Call the controller method and verify the response
        ResponseEntity<Boolean> response = cargoController.deleteCargo(2L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.getBody());
    }
}