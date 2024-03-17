package com.bkool.rest.mapper;

import com.bkool.rest.model.BikeModel;
import com.bkool.rest.model.CreateBikeModel;
import com.bkool.rest.model.ItemModel;
import entity.Bike;
import entity.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BikeRestMapper {

    private final ItemRestMapper itemRestMapper;

    public BikeRestMapper(ItemRestMapper itemRestMapper) {
        this.itemRestMapper = itemRestMapper;
    }

    public Bike toDomain(CreateBikeModel bikeModel) {
        String description = bikeModel.getDescription();
        String manufacturer = bikeModel.getManufacturer();
        String name = bikeModel.getName();
        Double price = bikeModel.getPrice();

        List<Item> parts = bikeModel.getParts().stream().map(itemRestMapper::toDomain).toList();
        return new Bike(null, name, description, price, manufacturer, parts);
    }

    public BikeModel toModel(Bike bike) {
        BikeModel bikeModel = new BikeModel();
        bikeModel.setId(bike.getId());
        bikeModel.setDescription(bike.getDescription());
        bikeModel.setManufacturer(bike.getManufacturer());
        bikeModel.setPrice(bike.getPrice());
        bikeModel.setName(bike.getName());

        List<ItemModel> itemModels = bike.getParts().stream().map(itemRestMapper::toModel).toList();
        bikeModel.setParts(itemModels);

        return bikeModel;
    }
}
