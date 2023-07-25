package com.railways.testproject.data.repository;

import com.railways.testproject.data.entity.ArrivalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrivalRecordRepository extends JpaRepository<ArrivalRecord, Long> {
}
