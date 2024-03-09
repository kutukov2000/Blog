package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.PostTagEntity;
import com.example.entities.PostTagPK;

public interface PostTagsRepository extends JpaRepository<PostTagEntity, PostTagPK> {
}
