package com.railways.testproject.data.repository;

import com.railways.testproject.data.entity.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WagonRepository extends JpaRepository<Wagon, Long  > {
}
