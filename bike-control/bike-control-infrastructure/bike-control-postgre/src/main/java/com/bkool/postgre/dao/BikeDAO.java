package com.bkool.postgre.dao;

import com.bkool.postgre.entity.BikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BikeDAO extends JpaRepository<BikeEntity, UUID> {

}
