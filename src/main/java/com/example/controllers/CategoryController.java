package com.example.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.category.CategoryCreateDTO;
import com.example.dtos.category.CategoryEditDTO;
import com.example.dtos.category.CategoryItemDTO;
import com.example.entities.CategoryEntity;
import com.example.mappers.CategoryMapper;
import com.example.repositories.CategoriesRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoriesRepository categoriesRepository;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryItemDTO>> getAll() {
        List<CategoryEntity> list = categoriesRepository.findAll();
        List<CategoryItemDTO> categoryDTOList = list.stream()
                .map(categoryEntity -> categoryMapper.categoryItemDTO(categoryEntity))
                .collect(Collectors.toList());
        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryItemDTO> create(CategoryCreateDTO categoryCreateDTO) {
        try {
            CategoryEntity categoryEntity = categoryMapper.categoryCreateDTO(categoryCreateDTO);
            categoriesRepository.save(categoryEntity);
            return new ResponseEntity<>(categoryMapper.categoryItemDTO(categoryEntity), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<CategoryItemDTO> edit(CategoryEditDTO updatedCategoryDTO) {
        CategoryEntity category = categoriesRepository.findById(updatedCategoryDTO.getId())
                .orElse(null);

        if (category == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            category = categoryMapper.categoryEditDTO(updatedCategoryDTO);
            categoriesRepository.save(category);
            return new ResponseEntity<>(categoryMapper.categoryItemDTO(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        CategoryEntity category = categoriesRepository.findById(id).orElse(null);

        if (category == null)
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);

        try {
            categoriesRepository.delete(category);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }
}
