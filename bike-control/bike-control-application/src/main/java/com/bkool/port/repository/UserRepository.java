package com.bkool.port.repository;

import entity.User;

public interface UserRepository {

    User findByName(String username);
}
