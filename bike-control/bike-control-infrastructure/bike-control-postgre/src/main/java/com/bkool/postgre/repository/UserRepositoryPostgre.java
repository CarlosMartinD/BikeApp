package com.bkool.postgre.repository;

import com.bkool.postgre.dao.UserDAO;
import com.bkool.postgre.entity.UserEntity;
import com.bkool.postgre.mapper.UserEntityMapper;
import com.bkool.port.repository.UserRepository;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryPostgre implements UserRepository {

    private final UserEntityMapper userEntityMapper;

    private final UserDAO userDAO;

    @Override
    public User findByName(String username) {
        Optional<UserEntity> userDTO = userDAO.findById(username);
        return userDTO.map(userEntityMapper::toDomain).orElse(null);

    }
}
