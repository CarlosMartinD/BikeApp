package com.bkool.usecase;

import com.bkool.port.repository.BikeRepository;
import com.bkool.port.usecase.CreateBike;
import com.bkool.usecase.params.CreateBikeParams;
import entity.Bike;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class CreateBikeUseCase implements CreateBike {

    private final BikeRepository bikeRepository;

    public CreateBikeUseCase(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public Bike execute(CreateBikeParams params) {
        return bikeRepository.save(params.getBike());
    }
}
