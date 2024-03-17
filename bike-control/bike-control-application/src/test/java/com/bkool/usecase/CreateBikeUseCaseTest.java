package com.bkool.usecase;

import com.bkool.port.repository.BikeRepository;
import com.bkool.usecase.params.CreateBikeParams;
import entity.Bike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateBikeUseCaseTest {

    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private CreateBikeUseCase createBikeUseCase;

    @Test
    void shouldCreateGivenBike() {
        Bike bike = mock(Bike.class);
        CreateBikeParams createBikeParams = new CreateBikeParams(bike);

        createBikeUseCase.execute(createBikeParams);

        verify(bikeRepository).save(bike);
    }
}