package com.bkool.rest;

import com.bkool.port.usecase.CreateBike;
import com.bkool.port.usecase.GetBike;
import com.bkool.rest.mapper.BikeRestMapper;
import com.bkool.rest.model.BikeModel;
import com.bkool.rest.model.CreateBikeModel;
import com.bkool.usecase.params.CreateBikeParams;
import com.bkool.usecase.params.GetBikeParams;
import entity.Bike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BikeControllerTest {

    @Mock
    private BikeRestMapper bikeRestMapper;

    @Mock
    private CreateBike createBike;

    @Mock
    private GetBike getBike;

    @InjectMocks
    private BikeController bikeController;

    @Test
    void shouldCreateBike() {
        CreateBikeModel createBikeModel = new CreateBikeModel();
        Bike bikeToSave = mock(Bike.class);
        BikeModel expectedBody = mock(BikeModel.class);
        ArgumentCaptor<CreateBikeParams> argument = ArgumentCaptor.forClass(CreateBikeParams.class);

        when(bikeRestMapper.toDomain(createBikeModel)).thenReturn(bikeToSave);
        when(createBike.execute(any())).thenReturn(bikeToSave);
        when(bikeRestMapper.toModel(bikeToSave)).thenReturn(expectedBody);

        ResponseEntity<BikeModel> response = bikeController.createBike(createBikeModel);

        verify(createBike).execute(argument.capture());
        assertEquals(bikeToSave, argument.getValue().getBike());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBody, response.getBody());
    }

    @Test
    void shouldGetBikes() {
        Optional<String> name = Optional.of("exampleName");
        Optional<String> manufacturer = Optional.of("exampleManufacturer");
        Optional<String> itemType = Optional.of("exampleItemType");
        Optional<String> direction = Optional.of("exampleDirection");
        List<Bike> bikes = List.of();

        ArgumentCaptor<GetBikeParams> getBikeParamsArgumentCaptor = ArgumentCaptor.forClass(GetBikeParams.class);
        when(getBike.execute(any(GetBikeParams.class))).thenReturn(bikes);

        ResponseEntity<List<BikeModel>> response = bikeController.findBikes(name, manufacturer, itemType, direction);

        verify(getBike).execute(getBikeParamsArgumentCaptor.capture());
        GetBikeParams paramsCaptured = getBikeParamsArgumentCaptor.getValue();

        assertEquals(name, paramsCaptured.getName());
        assertEquals(manufacturer, paramsCaptured.getManufacturer());
        assertEquals(itemType, paramsCaptured.getItemType());
        assertEquals(direction, paramsCaptured.getDirection());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bikes.size(), response.getBody().size());
    }
}