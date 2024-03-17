package com.bkool.postgre.dao;

import com.bkool.postgre.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemDAO extends JpaRepository<ItemEntity, UUID> {
}
