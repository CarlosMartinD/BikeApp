package com.bkool.usecase;

import com.bkool.port.repository.BikeRepository;
import com.bkool.port.usecase.GetBike;
import com.bkool.usecase.params.GetBikeParams;
import entity.Bike;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetBikeUseCase implements GetBike {

    private final BikeRepository bikeRepository;

    public GetBikeUseCase(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public List<Bike> execute(GetBikeParams params) {
        return bikeRepository.findAll(params.getName(), params.getManufacturer(), params.getItemType(), params.getDirection());
    }
}
