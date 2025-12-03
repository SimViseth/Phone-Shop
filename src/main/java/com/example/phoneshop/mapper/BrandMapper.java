package com.example.phoneshop.mapper;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand toBrand(BrandDTO brandDTO);  // map from DTO to Entity
    BrandDTO toDTO(Brand brand);   // map from Entity to DTO
}
