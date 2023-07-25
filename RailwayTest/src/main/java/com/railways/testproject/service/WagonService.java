package com.railways.testproject.service;

import com.railways.testproject.data.entity.Wagon;
import com.railways.testproject.data.repository.WagonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WagonService {

    private static final Logger logger = LoggerFactory.getLogger(WagonService.class);
    private final WagonRepository wagonRepository;

    @Autowired
    public WagonService(WagonRepository wagonRepository) {
        this.wagonRepository = wagonRepository;
    }

    public Wagon createWagon(Wagon wagon) {
        logger.info("Created new wagon with ID {}: {}", wagon.getId(), wagon);
        return wagonRepository.save(wagon);
    }

    public List<Wagon> getAllWagons() {
        return wagonRepository.findAll();
    }

    public Optional<Wagon> getWagonById(Long id) {
        Optional<Wagon> wagon = wagonRepository.findById(id);
        if (wagon.isPresent()) {
            logger.info("Retrieved wagon with ID {}: {}", id, wagon.get());
            return wagonRepository.findById(id);
        } else {
            logger.warn("Wagon with ID {} not found.", id);
            return Optional.empty();
        }
    }

    public Wagon updateWagon(Long id, Wagon wagon) {
        Optional<Wagon> existingWagon = wagonRepository.findById(id);
        if (existingWagon.isPresent()) {
            Wagon updatedWagon = existingWagon.get();
            updatedWagon.setNumber(wagon.getNumber());
            updatedWagon.setCurrentStation(wagon.getCurrentStation());
            updatedWagon.setArrivalRecord(wagon.getArrivalRecord());
            logger.info("Updated wagon with ID {}: {}", id, wagon);
            return wagonRepository.save(updatedWagon);
        } else {
            logger.warn("Attempted to update non-existent wagon with ID: {}", id);
            return null;
        }
    }

    public void deleteWagonById(Long id) {
        if (wagonRepository.existsById(id)) {
            wagonRepository.deleteById(id);
            logger.info("Deleted wagon with ID: {}", id);
        } else {
            logger.warn("Attempted to delete non-existent wagon with ID: {}", id);
        }
    }
}
