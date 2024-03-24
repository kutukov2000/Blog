package com.example.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.post.PostCreateDTO;
import com.example.dtos.post.PostEditDTO;
import com.example.dtos.post.PostItemDTO;
import com.example.dtos.post.PostWithTagsDTO;
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
        List<PostItemDTO> postDTOList = postMapper.postsToPostItemDTOs(list);
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostWithTagsDTO> getById(@PathVariable int id) {
        PostEntity post = postsRepository.findById(id).orElse(null);

        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PostWithTagsDTO postWithTagsDTO = postMapper.postEntityToPostWithTagsDTO(post);

        return new ResponseEntity<>(postWithTagsDTO, HttpStatus.OK);
    }

    @GetMapping("byCategoryId/{categoryId}")
    public ResponseEntity<List<PostItemDTO>> getByCategoryId(@PathVariable int categoryId) {
        List<PostEntity> list = postsRepository.findByCategoryId(categoryId);
        List<PostItemDTO> postDTOList = postMapper.postsToPostItemDTOs(list);
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostItemDTO> createPost(@RequestBody PostCreateDTO postCreateDTO) {
        try {
            PostEntity postEntity = postMapper.postCreateDTOToEntity(postCreateDTO);
            postsRepository.save(postEntity);
            return new ResponseEntity<>(postMapper.postItemDTO(postEntity), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<PostItemDTO> editPost(@PathVariable int id, @RequestBody PostEditDTO postEditDTO) {
        PostEntity existingPost = postsRepository.findById(id)
                .orElse(null);
        if (existingPost == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            existingPost = postMapper.postEditDTOToEntity(postEditDTO, existingPost);
            postsRepository.save(existingPost);
            return new ResponseEntity<>(postMapper.postItemDTO(existingPost), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
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
