package com.railways.testproject.service;

import com.railways.testproject.data.entity.Cargo;
import com.railways.testproject.data.entity.Station;
import com.railways.testproject.data.entity.WagonPassport;
import com.railways.testproject.data.repository.CargoRepository;
import com.railways.testproject.data.repository.StationRepository;
import com.railways.testproject.data.repository.WagonPassportRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestDataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);

    private final StationRepository stationRepository;
    private final CargoRepository cargoRepository;
    private final WagonPassportRepository wagonPassportRepository;


    @Override
    public void run(String... args) {
        Station station1 = new Station(1L, "Station A");
        Station station2 = new Station(2L, "Station B");
        Station station3 = new Station(3L, "Station C");
        List<Station> stationList = new ArrayList<>(Arrays.asList(station1, station2, station3));

        Cargo cargo1 = new Cargo(1L, "C001", "Cargo 1");
        Cargo cargo2 = new Cargo(2L, "C002", "Cargo 2");
        Cargo cargo3 = new Cargo(3L, "C003", "Cargo 3");
        List<Cargo> cargoList = new ArrayList<>(Arrays.asList(cargo1, cargo2, cargo3));

        WagonPassport passport1 = new WagonPassport(1L, "W001", "Type 1", 10.5, 25.0);
        WagonPassport passport2 = new WagonPassport(2L, "W002", "Type 2", 12.5, 35.0);
        WagonPassport passport3 = new WagonPassport(3L, "W003", "Type 3", 14.5, 45.0);
        List<WagonPassport> passportList = new ArrayList<>(Arrays.asList(passport1, passport2, passport3));

        stationRepository.saveAll(stationList);
        cargoRepository.saveAll(cargoList);
        wagonPassportRepository.saveAll(passportList);
        logger.info("Test data has been added");
    }
}