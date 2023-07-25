CREATE TABLE station (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE wagon (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       number VARCHAR(255) NOT NULL,
                       current_station_id BIGINT,
                       position INTEGER,
                       total_wagons INTEGER,
                       FOREIGN KEY (current_station_id) REFERENCES station(id)
);

CREATE TABLE arrival_record (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                arrival_time TIMESTAMP,
                                station_id BIGINT,
                                FOREIGN KEY (station_id) REFERENCES station(id)
);

CREATE TABLE cargo (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       code VARCHAR(255) NOT NULL,
                       name VARCHAR(255) NOT NULL
);

CREATE TABLE wagon_passport (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                number VARCHAR(255) NOT NULL,
                                type VARCHAR(255) NOT NULL,
                                tare_weight DOUBLE NOT NULL,
                                carrying_capacity DOUBLE NOT NULL
);