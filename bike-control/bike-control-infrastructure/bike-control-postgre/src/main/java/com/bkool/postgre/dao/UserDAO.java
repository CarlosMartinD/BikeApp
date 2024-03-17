package com.bkool.postgre.dao;

import com.bkool.postgre.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserEntity, String> {
}
