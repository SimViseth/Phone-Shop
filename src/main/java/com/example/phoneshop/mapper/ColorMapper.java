package com.example.phoneshop.mapper;

import com.example.phoneshop.dto.ColorDTO;
import com.example.phoneshop.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);
    Color toColor(ColorDTO colorDTO);

    ColorDTO toDto(Color color);
}
