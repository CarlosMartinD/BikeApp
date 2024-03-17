package com.bkool.postgre.mapper;

import com.bkool.postgre.entity.UserEntity;
import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityMapperTest {

    private final UserEntityMapper userEntityMapper = new UserEntityMapper();

    @Test
    void shouldMapFromEntityToDomain() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("password");
        userEntity.setUsername("username");

        User user = userEntityMapper.toDomain(userEntity);

        assertEquals(userEntity.getUsername(), user.getUsername());
        assertEquals(userEntity.getPassword(), user.getPassword());
    }
}