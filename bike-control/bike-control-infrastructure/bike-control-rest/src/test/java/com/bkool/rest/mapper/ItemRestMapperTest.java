package com.bkool.rest.mapper;

import com.bkool.rest.model.ItemModel;
import com.bkool.rest.model.ItemWithoutIdModel;
import entity.Item;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemRestMapperTest {

    private final ItemRestMapper itemRestMapper = new ItemRestMapper();

    @Test
    void shouldMapToDomainWithoutId() {
        ItemWithoutIdModel itemWithoutIdModel = new ItemWithoutIdModel();
        itemWithoutIdModel.setModel("model");
        itemWithoutIdModel.setDescription("description");
        itemWithoutIdModel.setType("tipo");

        Item item = itemRestMapper.toDomain(itemWithoutIdModel);

        assertEquals(itemWithoutIdModel.getModel(), item.getModel());
        assertEquals(itemWithoutIdModel.getType(), item.getType());
        assertEquals(itemWithoutIdModel.getDescription(), item.getDescription());
        assertNull(item.getId());
    }

    @Test
    void shouldMapToModel() {
        Item item = new Item(UUID.randomUUID(), "model" ,"description" , "type");

        ItemModel itemModel = itemRestMapper.toModel(item);

        assertEquals(item.getModel(), itemModel.getModel());
        assertEquals(item.getType(), itemModel.getType());
        assertEquals(item.getDescription(), itemModel.getDescription());
        assertEquals(item.getId(), itemModel.getId());
    }
}