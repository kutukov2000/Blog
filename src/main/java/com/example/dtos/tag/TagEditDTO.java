package com.example.dtos.tag;

import lombok.Data;

@Data
public class TagEditDTO {
    private int id;
    private String name;
    private String urlSlug;
    private String description;
}
