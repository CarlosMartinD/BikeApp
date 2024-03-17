package com.bkool.port.repository;

import entity.Bike;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BikeRepository {

    Bike save(Bike bike);

    List<Bike> findAll(Optional<String> name, Optional<String> manufacturer, Optional<String> itemType, Optional<String> direction);
}
