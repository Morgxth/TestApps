package com.railways.testproject.controller;

import com.railways.testproject.data.entity.Cargo;
import com.railways.testproject.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController {
    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping({"/createc"})
    public ResponseEntity<Cargo> createCargo(@RequestBody Cargo cargo) {
        Cargo createdCargo = cargoService.createCargo(cargo);
        return new ResponseEntity<>(createdCargo, HttpStatus.CREATED);
    }

    @GetMapping({"/getallc"})
    public ResponseEntity<List<Cargo>> getAllCargo() {
        List<Cargo> allCargo = cargoService.getAllCargo();
        return new ResponseEntity<>(allCargo, HttpStatus.OK);
    }

    @GetMapping("/getcbyid/{id}")
    public ResponseEntity<Cargo> getCargoById(@PathVariable Long id) {
        Cargo cargo = cargoService.getCargoById(id);
        if (cargo != null) {
            return new ResponseEntity<>(cargo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatec/{id}")
    public ResponseEntity<Cargo> updateCargo(@PathVariable Long id, @RequestBody Cargo updatedCargo) {
        Cargo updatedCargoInfo = cargoService.updateCargo(id, updatedCargo);
        if (updatedCargoInfo != null) {
            return new ResponseEntity<>(updatedCargoInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deletecbyid/{id}")
    public ResponseEntity<Boolean> deleteCargo(@PathVariable Long id) {
        boolean isDeleted = cargoService.deleteCargo(id);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}