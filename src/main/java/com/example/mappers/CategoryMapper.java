package com.example.mappers;

import org.mapstruct.Mapper;

import com.example.dtos.category.CategoryCreateDTO;
import com.example.dtos.category.CategoryEditDTO;
import com.example.dtos.category.CategoryItemDTO;
import com.example.entities.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryItemDTO categoryItemDTO(CategoryEntity category);

    CategoryEntity categoryCreateDTO(CategoryCreateDTO categoryCreateDTO);

    CategoryEntity categoryEditDTO(CategoryEditDTO categoryEditDTO);
}