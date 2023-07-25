package com.railways.testproject.service;
import com.railways.testproject.data.entity.Station;
import com.railways.testproject.data.repository.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    private static final Logger logger = LoggerFactory.getLogger(StationService.class);
    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Station createStation(Station station) {
        logger.info("Created new Station with ID {}: {}", station.getId(), station);
        return stationRepository.save(station);
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station updateStation(Long id, Station station) {
        Station existingStation = stationRepository.findById(id).orElse(null);
        if (existingStation != null) {
            existingStation.setName(station.getName());
            logger.info("Updated station with ID {}: {}", id, existingStation);
            return stationRepository.save(existingStation);
        }
        logger.warn("Attempted to update non-existent station with ID: {}", id);
        return null;
    }
    public Optional<Station> getStationById(Long id) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()) {
            logger.info("Retrieved station with ID {}: {}", id, station.get());
            return station;
        } else {
            logger.warn("station with ID {} not found.", id);
            return null;
        }
    }

    public Boolean deleteStationById(Long id) {
        if (stationRepository.existsById(id)) {
            logger.info("Deleted station with ID: {}", id);
            stationRepository.deleteById(id);
            return true;
        } else {
            logger.warn("Attempted to delete non-existent station with ID: {}", id);
            return false;
        }
    }
}