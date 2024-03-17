package com.bkool.port.usecase;

import com.bkool.usecase.params.GetBikeParams;
import entity.Bike;

import java.util.List;

public interface GetBike extends UseCase<List<Bike>, GetBikeParams> {

}
