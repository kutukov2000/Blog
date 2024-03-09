package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.CategoryEntity;

public interface CategoriesRepository extends JpaRepository<CategoryEntity, Integer> {
}
