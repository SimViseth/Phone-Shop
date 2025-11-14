package com.example.phoneshop.service;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand createBrand(Brand brand);

    Brand getById(Integer brandId);

    Brand updateBrand(Integer brandId, Brand brand);

    List<Brand> getAllBrands();
    List<Brand> getBrandByName(String name);
}
