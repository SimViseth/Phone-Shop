package com.example.phoneshop.service;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;

public interface BrandService {
    Brand createBrand(Brand brand);

    Brand getById(Integer brandId);

    Brand updateBrand(Integer brandId, Brand brand);
}
