package com.example.phoneshop.mapper;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapstruct {
    Brand toBrand(BrandDTO brandDTO);  // map from DTO to Entity
}
