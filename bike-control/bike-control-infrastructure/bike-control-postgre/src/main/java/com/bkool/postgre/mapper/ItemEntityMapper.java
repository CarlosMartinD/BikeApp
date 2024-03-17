package com.bkool.postgre.mapper;

import com.bkool.postgre.entity.BikeEntity;
import com.bkool.postgre.entity.ItemEntity;
import entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemEntityMapper {

    public ItemEntity toEntity(Item item, BikeEntity bikeEntity) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(item.getId());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setType(item.getType());
        itemEntity.setModel(item.getModel());
        itemEntity.setBike(bikeEntity);

        return itemEntity;
    }

    public Item toDomain(ItemEntity item) {
        UUID id = item.getId();
        String type = item.getType();
        String model = item.getModel();
        String description = item.getDescription();

        return new Item(id, model, type, description);
    }
}
