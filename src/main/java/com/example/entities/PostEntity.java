package com.example.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 5000, nullable = false)
    private String shortDescription;

    @Column(length = 5000, nullable = false)
    private String description;

    @Column(length = 5000, nullable = false)
    private String meta;

    @Column(length = 200, nullable = false)
    private String urlSlug;

    @Column(nullable = false)
    private boolean published;

    @Column(nullable = false)
    private LocalDateTime postedOn;

    private LocalDateTime modified;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CategoryEntity category;

    @OneToMany(mappedBy = "post")
    private List<PostTagEntity> postTags;
}


