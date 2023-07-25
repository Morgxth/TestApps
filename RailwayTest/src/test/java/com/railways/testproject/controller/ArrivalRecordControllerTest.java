package com.railways.testproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.railways.testproject.data.entity.ArrivalRecord;
import com.railways.testproject.data.entity.Station;
import com.railways.testproject.service.ArrivalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ArrivalRecordControllerTest {

    private ArrivalRecordController arrivalRecordController;
    private ArrivalRecordService arrivalRecordService;

    @BeforeEach
    public void setUp() {
        // Create a mock instance of ArrivalRecordService
        arrivalRecordService = mock(ArrivalRecordService.class);
        arrivalRecordController = new ArrivalRecordController(arrivalRecordService);
    }

    @Test
    public void testGetArrivalRecordById() {
        // Create a sample ArrivalRecord
        ArrivalRecord arrivalRecord = new ArrivalRecord();
        arrivalRecord.setId(1L);
        arrivalRecord.setArrivalTime(LocalDateTime.now());
        arrivalRecord.setStation(new Station(1L, "Station A"));

        // Set up the mock behavior for the service method
        when(arrivalRecordService.getArrivalRecordById(1L)).thenReturn(arrivalRecord);

        // Call the controller method and verify the response
        ResponseEntity<ArrivalRecord> response = arrivalRecordController.getArrivalRecordById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(arrivalRecord, response.getBody());
    }

    @Test
    public void testGetArrivalRecordById_NotFound() {
        // Set up the mock behavior for the service method
        when(arrivalRecordService.getArrivalRecordById(2L)).thenReturn(null);

        // Call the controller method and verify the response
        ResponseEntity<ArrivalRecord> response = arrivalRecordController.getArrivalRecordById(2L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllArrivalRecords() {
        // Create a list of sample ArrivalRecords
        List<ArrivalRecord> arrivalRecords = Arrays.asList(
                new ArrivalRecord(1L, LocalDateTime.now(), new ArrayList<>(), new Station(1L, "Station A")),
                new ArrivalRecord(2L, LocalDateTime.now(), new ArrayList<>(), new Station(2L, "Station B")),
                new ArrivalRecord(3L, LocalDateTime.now(), new ArrayList<>(), new Station(3L, "Station C"))
        );

        // Set up the mock behavior for the service method
        when(arrivalRecordService.getAllArrivalRecords()).thenReturn(arrivalRecords);

        // Call the controller method and verify the response
        ResponseEntity<List<ArrivalRecord>> response = arrivalRecordController.getAllArrivalRecords();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(arrivalRecords, response.getBody());
    }

    @Test
    public void testCreateArrivalRecord() {
        // Create a sample ArrivalRecord
        ArrivalRecord arrivalRecord = new ArrivalRecord();
        arrivalRecord.setArrivalTime(LocalDateTime.now());
        arrivalRecord.setStation(new Station(1L, "Station A"));

        // Set up the mock behavior for the service method
        when(arrivalRecordService.createArrivalRecord(any(ArrivalRecord.class))).thenReturn(arrivalRecord);

        // Call the controller method and verify the response
        ResponseEntity<ArrivalRecord> response = arrivalRecordController.createArrivalRecord(arrivalRecord);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(arrivalRecord, response.getBody());
    }

    @Test
    public void testUpdateArrivalRecord() {
        // Create a sample ArrivalRecord
        ArrivalRecord existingRecord = new ArrivalRecord();
        existingRecord.setId(1L);
        existingRecord.setArrivalTime(LocalDateTime.now());
        existingRecord.setStation(new Station(1L, "Station A"));

        // Create an updated ArrivalRecord
        ArrivalRecord updatedRecord = new ArrivalRecord();
        updatedRecord.setId(1L);
        updatedRecord.setArrivalTime(LocalDateTime.now());
        updatedRecord.setStation(new Station(2L, "Station B"));

        // Set up the mock behavior for the service method
        when(arrivalRecordService.updateArrivalRecord(eq(1L), any(ArrivalRecord.class))).thenReturn(updatedRecord);

        // Call the controller method and verify the response
        ResponseEntity<ArrivalRecord> response = arrivalRecordController.updateArrivalRecord(1L, updatedRecord);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedRecord, response.getBody());
    }

    @Test
    public void testUpdateArrivalRecord_NotFound() {
        // Create an updated ArrivalRecord
        ArrivalRecord updatedRecord = new ArrivalRecord();
        updatedRecord.setId(2L);
        updatedRecord.setArrivalTime(LocalDateTime.now());
        updatedRecord.setStation(new Station(2L, "Station B"));

        // Set up the mock behavior for the service method
        when(arrivalRecordService.updateArrivalRecord(eq(2L), any(ArrivalRecord.class))).thenReturn(null);

        // Call the controller method and verify the response
        ResponseEntity<ArrivalRecord> response = arrivalRecordController.updateArrivalRecord(2L, updatedRecord);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteArrivalRecord() {
        // Call the controller method and verify the response
        ResponseEntity<Void> response = arrivalRecordController.deleteArrivalRecord(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verify that the service method was called with the correct ID
        verify(arrivalRecordService, times(1)).deleteArrivalRecord(1L);
    }
}