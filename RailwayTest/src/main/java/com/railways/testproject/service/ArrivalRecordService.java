package com.railways.testproject.service;

import com.railways.testproject.data.entity.ArrivalRecord;
import com.railways.testproject.data.repository.ArrivalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrivalRecordService {

    private static final Logger logger = LoggerFactory.getLogger(ArrivalRecordService.class);
    private final ArrivalRecordRepository arrivalRecordRepository;

    @Autowired
    public ArrivalRecordService(ArrivalRecordRepository arrivalRecordRepository) {
        this.arrivalRecordRepository = arrivalRecordRepository;
    }

    public ArrivalRecord getArrivalRecordById(Long id) {
        return arrivalRecordRepository.findById(id).orElse(null);
    }

    public List<ArrivalRecord> getAllArrivalRecords() {
        return arrivalRecordRepository.findAll();
    }

    public ArrivalRecord createArrivalRecord(ArrivalRecord arrivalRecord) {
        logger.info("Saved ArrivalRecord: {}", arrivalRecord);
        return arrivalRecordRepository.save(arrivalRecord);
    }

    public ArrivalRecord updateArrivalRecord(Long id, ArrivalRecord arrivalRecord) {
        ArrivalRecord existingRecord = arrivalRecordRepository.findById(id).orElse(null);
        if (existingRecord != null) {
            existingRecord.setArrivalTime(arrivalRecord.getArrivalTime());
            existingRecord.setStation(arrivalRecord.getStation());
            existingRecord.setWagons(arrivalRecord.getWagons());
            logger.info("Updated ArrivalRecord with ID {}: {}", id, existingRecord);
            return arrivalRecordRepository.save(existingRecord);
        } else {
            logger.warn("Attempted to update non-existent ArrivalRecord with ID: {}", id);
            return null;
        }
    }

    public boolean deleteArrivalRecord(Long id) {
        if (arrivalRecordRepository.existsById(id)) {
            arrivalRecordRepository.deleteById(id);
            logger.info("Deleted ArrivalRecord with ID: {}", id);
            return true;
        } else {
            logger.warn("Attempted to delete non-existent ArrivalRecord with ID: {}", id);
            return false;
        }
    }
}




