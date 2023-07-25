package com.railways.testproject.controller;

import com.railways.testproject.data.entity.WagonPassport;
import com.railways.testproject.service.WagonPassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wagon-passport")
public class WagonPassportController {
    private final WagonPassportService wagonPassportService;

    @Autowired
    public WagonPassportController(WagonPassportService wagonPassportService) {
        this.wagonPassportService = wagonPassportService;
    }


    @PostMapping({"/createwp"})
    public ResponseEntity<WagonPassport> createWagonPassport(@RequestBody WagonPassport wagonPassport) {
        WagonPassport createdPassport = wagonPassportService.createWagonPassport(wagonPassport);
        return new ResponseEntity<>(createdPassport, HttpStatus.CREATED);
    }

    @GetMapping({"/getallwp"})
    public ResponseEntity<List<WagonPassport>> getAllWagonPassports() {
        List<WagonPassport> wagonPassports = wagonPassportService.getAllWagonPassports();
        return new ResponseEntity<>(wagonPassports, HttpStatus.OK);
    }

    @GetMapping("/getwp/{id}")
    public ResponseEntity<WagonPassport> getWagonPassportById(@PathVariable Long id) {
        WagonPassport wagonPassport = wagonPassportService.getWagonPassportById(id);
        if (wagonPassport != null) {
            return new ResponseEntity<>(wagonPassport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatewp/{id}")
    public ResponseEntity<WagonPassport> updateWagonPassport(@PathVariable Long id, @RequestBody WagonPassport updatedWagonPassport) {
        WagonPassport updatedPassport = wagonPassportService.updateWagonPassport(id, updatedWagonPassport);
        if (updatedPassport != null) {
            return new ResponseEntity<>(updatedPassport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletewp/{id}")
    public ResponseEntity<Boolean> deleteWagonPassport(@PathVariable Long id) {
        boolean isDeleted = wagonPassportService.deleteWagonPassport(id);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}
