package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.entity.Brand;
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
}
