package com.bkool.postgre.mapper;

import com.bkool.postgre.entity.BikeEntity;
import com.bkool.postgre.entity.ItemEntity;
import entity.Bike;
import entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BikeEntityMapper {

    private final ItemEntityMapper itemDTOMapper;

    public BikeEntity toEntity(Bike bike) {
        BikeEntity bikeEntity = new BikeEntity();
        bikeEntity.setDescription(bike.getDescription());
        bikeEntity.setName(bike.getName());
        bikeEntity.setPrice(bike.getPrice());
        bikeEntity.setManufacturer(bike.getManufacturer());
        bikeEntity.setId(bike.getId());

        List<ItemEntity> partsIds = bike.getParts().stream().map(part -> itemDTOMapper.toEntity(part, bikeEntity)).toList();
        bikeEntity.setItems(partsIds);

        return bikeEntity;
    }

    public Bike toDomain(BikeEntity bikeEntity) {
        UUID id = bikeEntity.getId();
        String name = bikeEntity.getName();
        Double price = bikeEntity.getPrice();
        String description = bikeEntity.getDescription();
        String manufacturer = bikeEntity.getManufacturer();
        List<Item> items = bikeEntity.getItems().stream().map(itemDTOMapper::toDomain).toList();

        return new Bike(id, name, description, price, manufacturer, items);
    }
}
