package com.bkool.usecase.params;

import entity.Bike;

public class CreateBikeParams {

    private final Bike bike;

    public CreateBikeParams(Bike bike) {
        this.bike = bike;
    }

    public Bike getBike() {
        return bike;
    }
}
