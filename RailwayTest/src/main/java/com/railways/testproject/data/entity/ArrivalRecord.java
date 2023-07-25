package com.railways.testproject.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "arrival_record")
@AllArgsConstructor
@NoArgsConstructor
public class ArrivalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime arrivalTime;

    @OneToMany(mappedBy = "arrivalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wagon> wagons;

    @ManyToOne
    private Station station;

    public void addWagon(Wagon wagon) {
        wagons.add(wagon);
        wagon.setArrivalRecord(this);
    }

    public void removeWagon(Wagon wagon) {
        wagons.remove(wagon);
        wagon.setArrivalRecord(null);
    }
}