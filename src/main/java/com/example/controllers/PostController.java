package com.example.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.post.PostCreateDTO;
import com.example.dtos.post.PostEditDTO;
import com.example.dtos.post.PostItemDTO;
import com.example.entities.PostEntity;
import com.example.mappers.PostMapper;
import com.example.repositories.PostsRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/posts")
public class PostController {
    private final PostsRepository postsRepository;
    private final PostMapper postMapper;

    @GetMapping
    public ResponseEntity<List<PostItemDTO>> getAll() {
        List<PostEntity> list = postsRepository.findAll();
        List<PostItemDTO> postDTOList = list.stream()
                .map(postEntity -> postMapper.postItemDTO(postEntity))
                .collect(Collectors.toList());

        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostItemDTO> createPost(PostCreateDTO postCreateDTO) {
        try {
            PostEntity postEntity = postMapper.postCreateDTOToEntity(postCreateDTO);
            postEntity.setPostedOn(LocalDateTime.now());
            postEntity.setModified(LocalDateTime.now());

            postsRepository.save(postEntity);

            return new ResponseEntity<>(postMapper.postItemDTO(postEntity), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<PostItemDTO> editPost(PostEditDTO postEditDTO) {
        PostEntity existingPost = postsRepository.findById(postEditDTO.getId())
                .orElse(null);
        if (existingPost == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            PostEntity post = postMapper.postEditDTOToEntity(postEditDTO);
            post.setModified(LocalDateTime.now());
            post.setPostedOn(existingPost.getPostedOn());
            postsRepository.save(post);
            return new ResponseEntity<>(postMapper.postItemDTO(post), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        if (!postsRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        postsRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
