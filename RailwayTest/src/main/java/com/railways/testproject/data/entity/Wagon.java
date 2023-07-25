package com.railways.testproject.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "wagon")
@AllArgsConstructor
@NoArgsConstructor
public class Wagon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @ManyToOne
    private Station currentStation;

    @ManyToOne
    private ArrivalRecord arrivalRecord;


}