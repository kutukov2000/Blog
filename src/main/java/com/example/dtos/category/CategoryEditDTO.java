package com.example.dtos.category;

import lombok.Data;

@Data
public class CategoryEditDTO {
    private int id;
    private String name;
    private String urlSlug;
    private String description;
}
