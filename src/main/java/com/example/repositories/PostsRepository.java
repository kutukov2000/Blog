package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.PostEntity;

public interface PostsRepository extends JpaRepository<PostEntity, Integer> {
}
