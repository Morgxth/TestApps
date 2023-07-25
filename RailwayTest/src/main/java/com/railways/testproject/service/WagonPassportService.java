package com.railways.testproject.service;

import com.railways.testproject.data.entity.WagonPassport;
import com.railways.testproject.data.repository.WagonPassportRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WagonPassportService {

    private static final Logger logger = LoggerFactory.getLogger(WagonPassportService.class);
    private final WagonPassportRepository wagonPassportRepository;

    public WagonPassport createWagonPassport(WagonPassport wagonPassport) {
        logger.info("Created new Wagon passport with ID {}: {}", wagonPassport.getId(), wagonPassport);
        return wagonPassportRepository.save(wagonPassport);
    }

    public List<WagonPassport> getAllWagonPassports() {
        return wagonPassportRepository.findAll();
    }

    public WagonPassport getWagonPassportById(Long id) {
        Optional<WagonPassport> wagonPassport = wagonPassportRepository.findById(id);
        if (wagonPassport.isPresent()) {
            logger.info("Retrieved Wagon passport with ID {}: {}", id, wagonPassport.get());
            return wagonPassport.get();
        } else {
            logger.warn("Wagon passport with ID {} not found.", id);
            return null;
        }
    }

    public WagonPassport updateWagonPassport(Long id, WagonPassport updatedWagonPassport) {
        WagonPassport existingWagonPassport = wagonPassportRepository.findById(id).orElse(null);
        if (existingWagonPassport != null) {
            existingWagonPassport.setNumber(updatedWagonPassport.getNumber());
            existingWagonPassport.setType(updatedWagonPassport.getType());
            existingWagonPassport.setTareWeight(updatedWagonPassport.getTareWeight());
            existingWagonPassport.setCarryingCapacity(updatedWagonPassport.getCarryingCapacity());
            logger.info("Updated Wagon passport with ID {}: {}", id, existingWagonPassport);
            return wagonPassportRepository.save(existingWagonPassport);
        }
        logger.warn("Attempted to update non-existent Wagon passport with ID: {}", id);
        return null;
    }

    public Boolean deleteWagonPassport(Long id) {
        if (wagonPassportRepository.existsById(id)) {
            logger.info("Deleted Wagon passport with ID: {}", id);
            wagonPassportRepository.deleteById(id);
            return true;
        } else {
            logger.warn("Attempted to delete non-existent Wagon passport with ID: {}", id);
            return false;
        }
    }
}