package com.example.mappers;

import org.mapstruct.Mapper;

import com.example.dtos.tag.TagCreateDTO;
import com.example.dtos.tag.TagEditDTO;
import com.example.dtos.tag.TagItemDTO;
import com.example.entities.TagEntity;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagItemDTO tagItemDTO(TagEntity post);

    TagEntity tagCreateDTOToEntity(TagCreateDTO tagCreateDTO);

    TagEntity tagEditDTOToEntity(TagEditDTO tagEditDTO);
}
