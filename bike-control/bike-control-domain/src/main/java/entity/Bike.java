package entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Bike implements Serializable {

    private final UUID id;
    private final String name;
    private final String description;
    private final Double price;
    private final String manufacturer;

    private final List<Item> parts;

    public Bike(UUID id, String name, String description, Double price, String manufacturer, List<Item> parts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.manufacturer = manufacturer;
        this.parts = parts;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public List<Item> getParts() {
        return parts;
    }
}
