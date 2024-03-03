package com.example.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class PostTagPK implements Serializable {
    private PostEntity post;
    private TagEntity tag;
}
