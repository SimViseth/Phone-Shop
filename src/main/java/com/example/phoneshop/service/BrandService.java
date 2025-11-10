package com.example.phoneshop.service;

import com.example.phoneshop.entity.Brand;

public interface BrandService {
    Brand createBrand(Brand brand);

    Brand getById(Integer brandId);
}
