package com.railways.testproject.service;

import com.railways.testproject.data.entity.Cargo;
import com.railways.testproject.data.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CargoService {

    private static final Logger logger = LoggerFactory.getLogger(CargoService.class);
    private final CargoRepository cargoRepository;

    public Cargo createCargo(Cargo cargo) {
        logger.info("Created new cargo with ID {}: {}", cargo.getId(), cargo);
        return cargoRepository.save(cargo);
    }

    public List<Cargo> getAllCargo() {
        return cargoRepository.findAll();
    }

    public Cargo getCargoById(Long id) {
        Optional<Cargo> cargo = cargoRepository.findById(id);
        if (cargo.isPresent()) {
            logger.info("Retrieved cargo with ID {}: {}", id, cargo.get());
            return cargoRepository.findById(id).orElse(null);
        } else {
            logger.warn("Cargo with ID {} not found.", id);
            return null;
        }
    }

    public Cargo updateCargo(Long id, Cargo updatedCargo) {
        Cargo existingCargo = cargoRepository.findById(id).orElse(null);
        if (existingCargo != null) {
            existingCargo.setCode(updatedCargo.getCode());
            existingCargo.setName(updatedCargo.getName());
            logger.info("Updated cargo with ID {}: {}", id, existingCargo);
            return cargoRepository.save(existingCargo);
        }
        logger.warn("Attempted to update non-existent cargo with ID: {}", id);
        return null;
    }

    public Boolean deleteCargo(Long id) {
        if (cargoRepository.existsById(id)) {
            logger.info("Deleted cargo with ID: {}", id);
            cargoRepository.deleteById(id);
            return true;
        } else {
            logger.warn("Attempted to delete non-existent ArrivalRecord with ID: {}", id);
            return false;
        }
    }
}
