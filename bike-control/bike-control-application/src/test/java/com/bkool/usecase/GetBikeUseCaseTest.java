package com.bkool.usecase;

import com.bkool.port.repository.BikeRepository;
import com.bkool.usecase.params.GetBikeParams;
import entity.Bike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetBikeUseCaseTest {

    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private GetBikeUseCase getBikeUseCase;

    @Test
    void shouldGetBikesGivenParams() {
        Optional<String> name = Optional.of("NAME");
        Optional<String> manufacturer = Optional.of("manufacturer");
        Optional<String> itemType = Optional.of("itemType");
        Optional<String> direction = Optional.of("asc");

        GetBikeParams createBikeParams = new GetBikeParams(name, manufacturer, itemType, direction);

        List<Bike> repositoryResult = List.of(mock(Bike.class));
        when(bikeRepository.findAll(name, manufacturer, itemType, direction)).thenReturn(repositoryResult);

        List<Bike> bikes = getBikeUseCase.execute(createBikeParams);

        assertEquals(repositoryResult, bikes);
    }
}