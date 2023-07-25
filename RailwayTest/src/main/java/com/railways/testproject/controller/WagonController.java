package com.railways.testproject.controller;
import com.railways.testproject.data.entity.Wagon;
import com.railways.testproject.service.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wagons")
public class WagonController {

    private final WagonService wagonService;

    @Autowired
    public WagonController(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wagon> getWagonById(@PathVariable Long id) {
        Optional<Wagon> wagon = wagonService.getWagonById(id);
        return wagon.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Wagon>> getAllWagons() {
        List<Wagon> wagons = wagonService.getAllWagons();
        return ResponseEntity.ok(wagons);
    }

    @PostMapping
    public ResponseEntity<Wagon> createWagon(@RequestBody Wagon wagon) {
        Wagon createdWagon = wagonService.createWagon(wagon);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWagon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wagon> updateWagon(@PathVariable Long id, @RequestBody Wagon wagon) {
        Wagon updatedWagon = wagonService.updateWagon(id, wagon);
        if (updatedWagon != null) {
            return ResponseEntity.ok(updatedWagon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWagon(@PathVariable Long id) {
        wagonService.deleteWagonById(id);
        return ResponseEntity.noContent().build();
    }
}
