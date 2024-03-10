package com.example.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.dtos.category.CategoryCreateDTO;
import com.example.dtos.category.CategoryEditDTO;
import com.example.dtos.category.CategoryItemDTO;
import com.example.entities.CategoryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryItemDTO categoryItemDTO(CategoryEntity category);

    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(categoryCreateDTO.getName()))")
    CategoryEntity categoryCreateDTO(CategoryCreateDTO categoryCreateDTO);

    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(categoryEditDTO.getName()))")
    CategoryEntity categoryEditDTO(CategoryEditDTO categoryEditDTO);

    List<CategoryItemDTO> categoriesToCategoryItemDTOs(List<CategoryEntity> categories);
}