package com.railways.testproject.data.repository;

import com.railways.testproject.data.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
