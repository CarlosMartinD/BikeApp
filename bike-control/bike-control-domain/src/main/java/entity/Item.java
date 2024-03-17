package entity;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

public class Item implements Serializable {

    private final UUID id;

    private final String model;

    private final String type;

    private final String description;

    public Item(UUID id, String model, String type, String description) {
        this.id = id;
        this.model = model;
        this.type = type;
        this.description = description;
    }


    public UUID getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
