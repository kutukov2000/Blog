package com.example.dtos.post;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PostWithTagsDTO {
    private int id;
    private String title;
    private String shortDescription;
    private String description;
    private String meta;
    private String urlSlug;
    private boolean published;
    private LocalDateTime postedOn;
    private LocalDateTime modified;
    private int categoryId;
    private List<String> tags;
}
