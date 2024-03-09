package com.example.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dtos.category.CategoryCreateDTO;
import com.example.dtos.category.CategoryEditDTO;
import com.example.dtos.category.CategoryItemDTO;
import com.example.entities.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryItemDTO categoryItemDTO(CategoryEntity category);

    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(categoryCreateDTO.getName()))")
    CategoryEntity categoryCreateDTO(CategoryCreateDTO categoryCreateDTO);

    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(categoryEditDTO.getName()))")
    CategoryEntity categoryEditDTO(CategoryEditDTO categoryEditDTO);
}