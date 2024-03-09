package com.example.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dtos.post.PostCreateDTO;
import com.example.dtos.post.PostEditDTO;
import com.example.dtos.post.PostItemDTO;
import com.example.entities.PostEntity;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "post.category.id", target = "categoryId")
    PostItemDTO postItemDTO(PostEntity post);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(postCreateDTO.getTitle()))")
    PostEntity postCreateDTOToEntity(PostCreateDTO postCreateDTO);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(postEditDTO.getTitle()))")
    PostEntity postEditDTOToEntity(PostEditDTO postEditDTO);
}
