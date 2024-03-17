package com.bkool.postgre.repository;


import com.bkool.postgre.dao.BikeDAO;
import com.bkool.postgre.entity.BikeEntity;
import com.bkool.postgre.entity.ItemEntity;
import com.bkool.postgre.mapper.BikeEntityMapper;
import entity.Bike;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BikeRepositoryPostgreTest {

    @Mock
    private BikeEntityMapper bikeEntityMapper;

    @Mock
    private BikeDAO bikeDAO;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<BikeEntity> criteriaQuery;

    @Mock
    private Root<BikeEntity> root;

    @Mock
    private Join<BikeEntity, ItemEntity> itemJoin;

    @Mock
    private TypedQuery<BikeEntity> typedQuery;

    @InjectMocks
    private BikeRepositoryPostgre bikeRepository;

    @Test
    void shouldSaveBikeThenReturnDatabaseResponse() {
        Bike bikeToSave = mock(Bike.class);
        BikeEntity databaseItem = mock(BikeEntity.class);
        Bike savedBike = mock(Bike.class);

        when(bikeEntityMapper.toEntity(bikeToSave)).thenReturn(databaseItem);
        when(bikeDAO.save(databaseItem)).thenReturn(databaseItem);
        when(bikeEntityMapper.toDomain(databaseItem)).thenReturn(savedBike);

        Bike returnedBike = bikeRepository.save(bikeToSave);

        assertEquals(savedBike, returnedBike);
    }

    @Test
    void shouldFindAllFilteringByName() {
        Optional<String> name = Optional.of("testName");
        Optional<String> manufacturer = Optional.empty();
        Optional<String> itemType = Optional.empty();
        Optional<String> direction = Optional.empty();
        Predicate predicate = mock(Predicate.class);

        mockCritera(predicate);
        when(criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name.get() + "%"))).thenReturn(predicate);

        List<Bike> expectedItem = mockResult();
        List<Bike> bikes = bikeRepository.findAll(name, manufacturer, itemType, direction);

        assertEquals(expectedItem, bikes);
    }

    @Test
    void shouldFindAllFilteringByManufacturer() {
        Optional<String> name = Optional.empty();
        Optional<String> manufacturer = Optional.of("manufacturer");
        Optional<String> itemType = Optional.empty();
        Optional<String> direction = Optional.empty();
        Predicate predicate = mock(Predicate.class);

        mockCritera(predicate);
        when(criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("manufacturer"), "%" + manufacturer.get() + "%"))).thenReturn(predicate);
        List<Bike> expectedResult = mockResult();

        List<Bike> bikes = bikeRepository.findAll(name, manufacturer, itemType, direction);

        assertEquals(expectedResult, bikes);
    }


    @Test
    void shouldFindAllFilteringByItemType() {
        Optional<String> name = Optional.empty();
        Optional<String> manufacturer = Optional.empty();
        Optional<String> itemType = Optional.of("type");
        Optional<String> direction = Optional.empty();
        Predicate predicate = mock(Predicate.class);
        Join join = mock(Join.class);
        Path path = mock(Path.class);

        mockCritera(predicate);
        when(root.join("items", JoinType.INNER)).thenReturn(join);
        when(join.get("type")).thenReturn(path);
        when(criteriaBuilder.and(predicate, criteriaBuilder.like(itemJoin.get("type"), "%" + itemType.get() + "%"))).thenReturn(predicate);
        List<Bike> expectedResult = mockResult();

        List<Bike> bikes = bikeRepository.findAll(name, manufacturer, itemType, direction);

        assertEquals(expectedResult, bikes);
    }

    @Test
    void shouldOrderInAscendingOrderByName() {
        Optional<String> name = Optional.empty();
        Optional<String> manufacturer = Optional.empty();
        Optional<String> itemType = Optional.empty();
        Optional<String> direction = Optional.of("asc");
        Predicate predicate = mock(Predicate.class);
        Path path = mock(Path.class);
        Order order = mock(Order.class);

        mockCritera(predicate);
        when(root.get("name")).thenReturn(path);
        when(criteriaBuilder.asc(path)).thenReturn(order);
        List<Bike> expectedResult = mockResult();

        List<Bike> bikes = bikeRepository.findAll(name, manufacturer, itemType, direction);

        assertEquals(expectedResult, bikes);
        verify(criteriaQuery).orderBy(any(Order.class));
    }

    @Test
    void shouldWithoutFilterOrOrdering() {
        Optional<String> name = Optional.empty();
        Optional<String> manufacturer = Optional.empty();
        Optional<String> itemType = Optional.empty();
        Optional<String> direction = Optional.empty();
        Predicate predicate = mock(Predicate.class);

        mockCritera(predicate);
        List<Bike> expectedResult = mockResult();

        List<Bike> bikes = bikeRepository.findAll(name, manufacturer, itemType, direction);

        assertEquals(expectedResult, bikes);
        verify(criteriaBuilder, never()).and(any());
        verify(criteriaQuery, never()).orderBy(any(Order.class));
    }

    private void mockCritera(Predicate predicate) {
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(BikeEntity.class)).thenReturn(criteriaQuery);
        when(criteriaBuilder.conjunction()).thenReturn(predicate);
        when(criteriaQuery.from(BikeEntity.class)).thenReturn(root);
    }

    private List<Bike> mockResult() {
        BikeEntity databaseBike = mock(BikeEntity.class);
        Bike domainBike = mock(Bike.class);
        List<BikeEntity> result = List.of(databaseBike);

        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(result);
        when(bikeEntityMapper.toDomain(databaseBike)).thenReturn(domainBike);

        return List.of(domainBike);
    }
}