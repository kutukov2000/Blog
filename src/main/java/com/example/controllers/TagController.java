package com.example.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.tag.TagCreateDTO;
import com.example.dtos.tag.TagEditDTO;
import com.example.dtos.tag.TagItemDTO;
import com.example.entities.TagEntity;
import com.example.mappers.TagMapper;
import com.example.repositories.TagsRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/tags")
public class TagController {
    private final TagsRepository tagsRepository;
    private final TagMapper tagMapper;

    @GetMapping
    ResponseEntity<List<TagItemDTO>> getAll() {
        List<TagEntity> list = tagsRepository.findAll();
        List<TagItemDTO> tagItemDTOList = tagMapper.tagsToTagItemDTOs(list);
        return new ResponseEntity<>(tagItemDTOList, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<TagItemDTO> create(TagCreateDTO tabCreateDTO) {
        try {
            TagEntity tag = tagMapper.tagCreateDTOToEntity(tabCreateDTO);
            tagsRepository.save(tag);
            return new ResponseEntity<>(tagMapper.tagItemDTO(tag), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    ResponseEntity<TagItemDTO> edit(TagEditDTO tagEditDTO) {
        try {
            TagEntity existedEntity = tagsRepository.findById(tagEditDTO.getId()).orElse(null);
            if (existedEntity == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            existedEntity = tagMapper.tagEditDTOToEntity(tagEditDTO);
            tagsRepository.save(existedEntity);
            return new ResponseEntity<>(tagMapper.tagItemDTO(existedEntity), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable int id) {
        if (!tagsRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tagsRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
