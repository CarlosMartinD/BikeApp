package com.bkool.port.usecase;

import com.bkool.usecase.params.CreateBikeParams;
import entity.Bike;

import java.util.UUID;

public interface CreateBike extends UseCase<Bike, CreateBikeParams> {
}
