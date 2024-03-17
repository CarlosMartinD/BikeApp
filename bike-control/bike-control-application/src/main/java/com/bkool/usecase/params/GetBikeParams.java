package com.bkool.usecase.params;

import java.util.Optional;

public class GetBikeParams {

    private final Optional<String> name;
    private final Optional<String> manufacturer;
    private final Optional<String> itemType;
    private final Optional<String> direction;

    public GetBikeParams(Optional<String> name, Optional<String> manufacturer, Optional<String> itemType, Optional<String> direction) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.itemType = itemType;
        this.direction = direction;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getManufacturer() {
        return manufacturer;
    }

    public Optional<String> getItemType() {
        return itemType;
    }

    public Optional<String> getDirection() {
        return direction;
    }
}
