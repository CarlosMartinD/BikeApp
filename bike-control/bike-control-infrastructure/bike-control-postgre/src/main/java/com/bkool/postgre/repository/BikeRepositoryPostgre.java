package com.bkool.postgre.repository;

import com.bkool.postgre.dao.BikeDAO;
import com.bkool.postgre.entity.BikeEntity;
import com.bkool.postgre.entity.ItemEntity;
import com.bkool.postgre.mapper.BikeEntityMapper;
import com.bkool.port.repository.BikeRepository;
import entity.Bike;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BikeRepositoryPostgre implements BikeRepository {

    private final BikeEntityMapper bikeEntityMapper;

    private final BikeDAO bikeDAO;

    private final EntityManager entityManager;

    @Override
    @CacheEvict(value = "bikes", allEntries = true)
    public Bike save(Bike bike) {
        BikeEntity document = bikeEntityMapper.toEntity(bike);
        return bikeEntityMapper.toDomain(bikeDAO.save(document));
    }

    @Override
    @Cacheable(value = "bikes")
    public List<Bike> findAll(Optional<String> name, Optional<String> manufacturer, Optional<String> itemType, Optional<String> direction) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BikeEntity> query = builder.createQuery(BikeEntity.class);
        Root<BikeEntity> root = query.from(BikeEntity.class);

        Predicate predicate = builder.conjunction();

        if (name.isPresent()) {
            predicate = builder.and(predicate, builder.like(root.get("name"), "%" + name.get() + "%"));
        }

        if (manufacturer.isPresent()) {
            predicate = builder.and(predicate, builder.like(root.get("manufacturer"), "%" + manufacturer.get() + "%"));
        }

        if (itemType.isPresent()) {
            Join<BikeEntity, ItemEntity> itemJoin = root.join("items", JoinType.INNER);
            predicate = builder.and(predicate, builder.like(itemJoin.get("type"), "%" + itemType.get() + "%"));
        }

        query.where(predicate);

        if (direction.isPresent()) {
            if (direction.get().equalsIgnoreCase("asc")) {
                query.orderBy(builder.asc(root.get("name")));
            } else if (direction.get().equalsIgnoreCase("desc")) {
                query.orderBy(builder.desc(root.get("name")));
            }
        }

        return entityManager.createQuery(query).getResultList().stream().map(bikeEntityMapper::toDomain).toList();
    }
}
