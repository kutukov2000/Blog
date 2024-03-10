package com.example.repositories;

import com.example.entities.UserEntity;
import com.example.entities.UserRoleEntity;
import com.example.entities.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRolePK> {
    List<UserRoleEntity> findByUser(UserEntity user);
}
