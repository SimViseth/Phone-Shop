package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;
import com.example.phoneshop.exception.ResourceNotFound;
import com.example.phoneshop.repository.BrandRepository;
import com.example.phoneshop.service.BrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImplement implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImplement(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFound("Brand", brandId));
    }

    @Override
    public Brand updateBrand(Integer brandId, Brand brand) {
        Brand findBrand = getById(brandId);
        findBrand.setName(brand.getName());
        return brandRepository.save(findBrand);
    }
}
