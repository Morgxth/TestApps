package com.railways.testproject.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "wagon_passport")
@AllArgsConstructor
@NoArgsConstructor
public class WagonPassport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String type;

    private double tareWeight;

    private double carryingCapacity;

}