package com.example.dtos.post;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostItemDTO {
    private int id;
    private String title;
    private String shortDescription;
    private String description;
    private String meta;
    private String urlslug;
    private boolean published;
    private LocalDateTime postedOn;
    private LocalDateTime modified;
    private int categoryId;
}
