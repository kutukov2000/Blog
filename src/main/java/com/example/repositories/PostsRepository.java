package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.PostEntity;

public interface PostsRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByCategoryId(int categoryId);
}
