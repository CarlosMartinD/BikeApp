package com.bkool.postgre.mapper;

import com.bkool.postgre.entity.BikeEntity;
import com.bkool.postgre.entity.ItemEntity;
import entity.Bike;
import entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeEntityMapperTest {

    @Mock
    private ItemEntityMapper itemEntityMapper;

    @InjectMocks
    private BikeEntityMapper bikeEntityMapper;

    @Test
    void shouldMapFromEntityToDomain() {
        ItemEntity itemEntity = mock(ItemEntity.class);
        Item item = mock(Item.class);

        BikeEntity bikeEntity = new BikeEntity();
        bikeEntity.setDescription("description");
        bikeEntity.setPrice(0.5D);
        bikeEntity.setManufacturer("manufacturer");
        bikeEntity.setId(UUID.randomUUID());
        bikeEntity.setItems(List.of(itemEntity));

        when(itemEntityMapper.toDomain(itemEntity)).thenReturn(item);
        Bike bike = bikeEntityMapper.toDomain(bikeEntity);

        assertEquals(bikeEntity.getId(), bike.getId());
        assertEquals(bikeEntity.getDescription(), bike.getDescription());
        assertEquals(bikeEntity.getPrice(), bike.getPrice());
        assertEquals(bikeEntity.getManufacturer(), bike.getManufacturer());
        assertEquals(bikeEntity.getItems().size(), bike.getParts().size());
    }


    @Test
    void shouldMapFromDomainToEntity() {
        ItemEntity itemEntity = mock(ItemEntity.class);
        Item item = mock(Item.class);

        Bike bike = new Bike(UUID.randomUUID(), "name", "description", 0.5, "manufacturer", List.of(item));

        when(itemEntityMapper.toEntity(eq(item), any(BikeEntity.class))).thenReturn(itemEntity);
        BikeEntity bikeEntity = bikeEntityMapper.toEntity(bike);

        assertEquals(bike.getId(), bikeEntity.getId());
        assertEquals(bike.getDescription(), bikeEntity.getDescription());
        assertEquals(bike.getPrice(), bikeEntity.getPrice());
        assertEquals(bike.getManufacturer(), bikeEntity.getManufacturer());
        assertEquals(bike.getParts().size(), bikeEntity.getItems().size());
    }
}