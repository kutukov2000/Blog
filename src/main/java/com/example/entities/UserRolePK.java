package com.example.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRolePK implements Serializable {
    private UserEntity user;
    private RoleEntity role;
}