package com.railways.testproject.controller;

import com.railways.testproject.data.entity.Station;
import com.railways.testproject.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/getstation/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable Long id) {
        Optional<Station> station = stationService.getStationById(id);
        return station.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getstations")
    public ResponseEntity<List<Station>> getAllStations() {
        List<Station> stations = stationService.getAllStations();
        return ResponseEntity.ok(stations);
    }

    @PostMapping("/createstation")
    public ResponseEntity<Station> createStation(@RequestBody Station station) {
        Station createdStation = stationService.createStation(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStation);
    }

    @PutMapping("/updatestation/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable Long id, @RequestBody Station station) {
        Station updatedStation = stationService.updateStation(id, station);
        if (updatedStation != null) {
            return ResponseEntity.ok(updatedStation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletestation/{id}")
    public ResponseEntity<Boolean> deleteStation(@PathVariable Long id) {
        stationService.deleteStationById(id);
        return ResponseEntity.noContent().build();
    }
}
