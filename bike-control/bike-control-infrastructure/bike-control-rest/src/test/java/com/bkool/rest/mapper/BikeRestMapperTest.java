package com.bkool.rest.mapper;

import com.bkool.rest.model.BikeModel;
import com.bkool.rest.model.CreateBikeModel;
import com.bkool.rest.model.ItemModel;
import com.bkool.rest.model.ItemWithoutIdModel;
import entity.Bike;
import entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeRestMapperTest {

    @Mock
    private ItemRestMapper itemRestMapper;

    @InjectMocks
    private BikeRestMapper bikeRestMapper;

    @Test
    void shouldMapToDomainWithoutId() {
        ItemWithoutIdModel itemWithoutIdModel = mock(ItemWithoutIdModel.class);
        CreateBikeModel bikeModel = new CreateBikeModel();
        bikeModel.setName("name");
        bikeModel.setPrice(0.5D);
        bikeModel.setManufacturer("manufacturer");
        bikeModel.setDescription("description");
        bikeModel.setParts(List.of(itemWithoutIdModel));

        when(itemRestMapper.toDomain(itemWithoutIdModel)).thenReturn(mock(Item.class));
        Bike bike = bikeRestMapper.toDomain(bikeModel);

        assertEquals(bikeModel.getName(), bike.getName());
        assertEquals(bikeModel.getPrice(), bike.getPrice());
        assertEquals(bikeModel.getManufacturer(), bike.getManufacturer());
        assertEquals(bikeModel.getDescription(), bike.getDescription());
        assertEquals(1, bike.getParts().size());
    }

    @Test
    void shouldMapToModel() {
        Item item = mock(Item.class);
        Bike bike = new Bike(UUID.randomUUID(), "name", "description",0.5D, "manufacturer", List.of(item));

        when(itemRestMapper.toModel(item)).thenReturn(mock(ItemModel.class));
        BikeModel bikeModel = bikeRestMapper.toModel(bike);

        assertEquals(bike.getName(), bikeModel.getName());
        assertEquals(bike.getPrice(), bikeModel.getPrice());
        assertEquals(bike.getManufacturer(), bikeModel.getManufacturer());
        assertEquals(bike.getDescription(), bikeModel.getDescription());
        assertEquals(1, bikeModel.getParts().size());
    }
}