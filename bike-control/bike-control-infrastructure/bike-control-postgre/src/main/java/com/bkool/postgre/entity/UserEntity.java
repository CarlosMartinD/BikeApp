package com.bkool.postgre.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
