package com.example.dtos.post;

import lombok.Data;

@Data
public class PostEditDTO {
    private int id;
    private String title;
    private String shortDescription;
    private String description;
    private String meta;
    private String urlslug;
    private boolean published;
    private int categoryId;
}