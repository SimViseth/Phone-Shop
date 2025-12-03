package com.example.phoneshop.mapper;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;

public class ResponseMapper {

    // map from Entity to DTO
    public static Brand toBrand(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        return brand;
    }

    // map from DTO to Entity
    public static BrandDTO toBrandDTO(Brand brand) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(brand.getName());
        return brandDTO;
    }
}
