package com.bkool.postgre.mapper;

import com.bkool.postgre.entity.UserEntity;
import entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public User toDomain(UserEntity userEntity) {
        return new User(userEntity.getUsername(), userEntity.getPassword());
    }
}

