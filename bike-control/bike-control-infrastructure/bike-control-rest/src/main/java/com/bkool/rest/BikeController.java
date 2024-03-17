package com.bkool.rest;


import com.bkool.port.usecase.CreateBike;
import com.bkool.port.usecase.GetBike;
import com.bkool.rest.api.BikeApi;
import com.bkool.rest.api.BikesApi;
import com.bkool.rest.mapper.BikeRestMapper;
import com.bkool.rest.model.BikeModel;
import com.bkool.rest.model.CreateBikeModel;
import com.bkool.usecase.params.CreateBikeParams;
import com.bkool.usecase.params.GetBikeParams;
import entity.Bike;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BikeController implements BikeApi, BikesApi {

    private final CreateBike createBike;

    private final GetBike getBike;

    private final BikeRestMapper bikeRestMapper;


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return BikeApi.super.getRequest();
    }

    @Override
    public ResponseEntity<BikeModel> createBike(CreateBikeModel createBikeModel) {
        Bike bike = bikeRestMapper.toDomain(createBikeModel);
        CreateBikeParams createBikeParams = new CreateBikeParams(bike);
        Bike savedBike = createBike.execute(createBikeParams);
        return ResponseEntity.ok(bikeRestMapper.toModel(savedBike));
    }

    @Override
    public ResponseEntity<List<BikeModel>> findBikes(Optional<String> name, Optional<String> manufacturer, Optional<String> itemType, Optional<String> direction) {
        GetBikeParams getBikeParams = new GetBikeParams(name, manufacturer, itemType, direction);
        List<Bike> bikes = getBike.execute(getBikeParams);
        return ResponseEntity.ok(bikes.stream().map(bikeRestMapper::toModel).toList());
    }
}
