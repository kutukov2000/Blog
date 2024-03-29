package com.example.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.dtos.tag.TagCreateDTO;
import com.example.dtos.tag.TagEditDTO;
import com.example.dtos.tag.TagItemDTO;
import com.example.entities.TagEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    TagItemDTO tagItemDTO(TagEntity post);

    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(tagCreateDTO.getName()))")
    TagEntity tagCreateDTOToEntity(TagCreateDTO tagCreateDTO);

    @Mapping(target = "urlSlug", expression = "java(com.example.services.UrlSlugGenerator.generateUrlSlug(tagEditDTO.getName()))")
    TagEntity tagEditDTOToEntity(TagEditDTO tagEditDTO);

    List<TagItemDTO> tagsToTagItemDTOs(List<TagEntity> tags);
}
