package com.bkool.rest.mapper;

import com.bkool.rest.model.ItemModel;
import com.bkool.rest.model.ItemWithoutIdModel;
import entity.Item;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemRestMapper {

    public Item toDomain(ItemWithoutIdModel itemModel) {
        String description = itemModel.getDescription();
        return new Item(null, itemModel.getModel(),itemModel.getType(), description);
    }

    public ItemModel toModel(Item item) {
        ItemModel itemModel = new ItemModel();
        itemModel.setId(item.getId());
        itemModel.setModel(item.getModel());
        itemModel.setDescription(item.getDescription());
        itemModel.setType(item.getType());

        return itemModel;
    }
}
