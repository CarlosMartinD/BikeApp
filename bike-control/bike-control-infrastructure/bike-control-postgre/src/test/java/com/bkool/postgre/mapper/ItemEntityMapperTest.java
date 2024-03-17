package com.bkool.postgre.mapper;

import com.bkool.postgre.entity.BikeEntity;
import com.bkool.postgre.entity.ItemEntity;
import entity.Item;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

class ItemEntityMapperTest {

    private final  ItemEntityMapper itemEntityMapper = new ItemEntityMapper();

    @Test
    void shouldMapFromDomainToEntity() {
        Item item = new Item(UUID.randomUUID(), "model", "type", "description");
        BikeEntity bikeEntity = mock(BikeEntity.class);

        ItemEntity entity = itemEntityMapper.toEntity(item, bikeEntity);

        assertEquals(item.getId(), entity.getId());
        assertEquals(item.getType(), entity.getType());
        assertEquals(item.getDescription(), entity.getDescription());
        assertEquals(item.getModel(), entity.getModel());
        assertEquals(bikeEntity, entity.getBike());
    }

    @Test
    void shouldMapFromEntityToDomain() {
        ItemEntity entity = new ItemEntity();
        entity.setId(UUID.randomUUID());
        entity.setType("type");
        entity.setModel("model");
        entity.setDescription("description");

        Item item = itemEntityMapper.toDomain(entity);

        assertEquals(entity.getId(), item.getId());
        assertEquals(entity.getType(), item.getType());
        assertEquals(entity.getDescription(), item.getDescription());
        assertEquals(entity.getModel(), item.getModel());
    }
}