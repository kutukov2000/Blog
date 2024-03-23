package com.example.dtos.post;

import lombok.Data;

@Data
public class PostEditDTO {
    private String title;
    private String shortDescription;
    private String description;
    private String meta;
    private boolean published;
    private int categoryId;
}
