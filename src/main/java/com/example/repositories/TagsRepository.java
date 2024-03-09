package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.TagEntity;

public interface TagsRepository extends JpaRepository<TagEntity, Integer> {
}
