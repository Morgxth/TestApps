package com.railways.testproject.data.repository;

import com.railways.testproject.data.entity.WagonPassport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WagonPassportRepository extends JpaRepository<WagonPassport, Long> {
}
