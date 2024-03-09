package com.example.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dtos.tag.TagCreateDTO;
import com.example.dtos.tag.TagEditDTO;
import com.example.dtos.tag.TagItemDTO;
import com.example.entities.TagEntity;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagItemDTO tagItemDTO(TagEntity post);

    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(tagCreateDTO.getName()))")
    TagEntity tagCreateDTOToEntity(TagCreateDTO tagCreateDTO);

    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(tagEditDTO.getName()))")
    TagEntity tagEditDTOToEntity(TagEditDTO tagEditDTO);
}
