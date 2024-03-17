package com.bkool.postgre.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "bikes")
public class BikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private String manufacturer;

    @OneToMany(mappedBy = "bike", cascade = CascadeType.ALL)
    private List<ItemEntity> items;
}
