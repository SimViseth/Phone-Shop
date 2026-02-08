package com.example.phoneshop.service;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand createBrand(Brand brand);

    Brand getById(Long brandId);

    Brand updateBrand(Long brandId, Brand brand);

    List<Brand> getAllBrands();
    List<Brand> getBrandByName(String name);
//    List<Brand> getBrands(Map<String, String> params);
    Page<Brand> getBrands(Map<String, String> params);
}