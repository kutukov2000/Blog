package com.example.mappers;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.dtos.post.PostCreateDTO;
import com.example.dtos.post.PostEditDTO;
import com.example.dtos.post.PostItemDTO;
import com.example.dtos.post.PostWithTagsDTO;
import com.example.entities.PostEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    @Mapping(source = "post.category.id", target = "categoryId")
    PostItemDTO postItemDTO(PostEntity post);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(postCreateDTO.getTitle()))")
    @Mapping(target = "postedOn", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "modified", expression = "java(java.time.LocalDateTime.now())")
    PostEntity postCreateDTOToEntity(PostCreateDTO postCreateDTO);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(postEditDTO.getTitle()))")
    @Mapping(target = "postedOn", expression = "java(existedPost.getPostedOn())")
    @Mapping(target = "modified", expression = "java(java.time.LocalDateTime.now())")
    PostEntity postEditDTOToEntity(PostEditDTO postEditDTO, @Context PostEntity existedPost);

    @Mapping(source = "post.category.id", target = "categoryId")
    List<PostItemDTO> postsToPostItemDTOs(List<PostEntity> posts);

    @Mapping(source = "post.category.id", target = "categoryId")
    @Mapping(target = "tags", expression = "java(post.getPostTags().stream().map(postTag -> postTag.getTag().getName()).collect(java.util.stream.Collectors.toList()))")
    PostWithTagsDTO postEntityToPostWithTagsDTO(PostEntity post);
}
