package com.railways.testproject.controller;

import com.railways.testproject.data.entity.ArrivalRecord;
import com.railways.testproject.service.ArrivalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arrival-records")
public class ArrivalRecordController {

    private final ArrivalRecordService arrivalRecordService;

    @Autowired
    public ArrivalRecordController(ArrivalRecordService arrivalRecordService) {
        this.arrivalRecordService = arrivalRecordService;
    }

    @GetMapping("/getarbyid/{id}")
    public ResponseEntity<ArrivalRecord> getArrivalRecordById(@PathVariable Long id) {
        ArrivalRecord arrivalRecord = arrivalRecordService.getArrivalRecordById(id);
        if (arrivalRecord != null) {
            return ResponseEntity.ok(arrivalRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping({"/getallar"})
    public ResponseEntity<List<ArrivalRecord>> getAllArrivalRecords() {
        List<ArrivalRecord> arrivalRecords = arrivalRecordService.getAllArrivalRecords();
        return ResponseEntity.ok(arrivalRecords);
    }

    @PostMapping("/createar")
    public ResponseEntity<ArrivalRecord> createArrivalRecord(@RequestBody ArrivalRecord arrivalRecord) {
        ArrivalRecord createdRecord = arrivalRecordService.createArrivalRecord(arrivalRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    @PutMapping("/updatear/{id}")
    public ResponseEntity<ArrivalRecord> updateArrivalRecord(@PathVariable Long id, @RequestBody ArrivalRecord arrivalRecord) {
        ArrivalRecord updatedRecord = arrivalRecordService.updateArrivalRecord(id, arrivalRecord);
        if (updatedRecord != null) {
            return ResponseEntity.ok(updatedRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletear/{id}")
    public ResponseEntity<Void> deleteArrivalRecord(@PathVariable Long id) {
        boolean deleted = arrivalRecordService.deleteArrivalRecord(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
