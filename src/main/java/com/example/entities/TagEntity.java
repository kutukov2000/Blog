package com.example.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String urlSlug;

    @Column(length = 200)
    private String description;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
    private List<PostTagEntity> postTags;
}
