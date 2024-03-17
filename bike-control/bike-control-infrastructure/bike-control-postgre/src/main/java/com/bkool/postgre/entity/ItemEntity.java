package com.bkool.postgre.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String type;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "bike_id", nullable = true)
    private BikeEntity bike;
}
