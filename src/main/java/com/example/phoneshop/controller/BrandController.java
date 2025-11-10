package com.example.phoneshop.controller;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;
import com.example.phoneshop.mapper.ResponseMapper;
import com.example.phoneshop.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/create-brand")
    public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO) {
        Brand brand = ResponseMapper.toBrand(brandDTO);
        brand = brandService.createBrand(brand);
        return ResponseEntity.ok(ResponseMapper.toBrandDTO(brand));
    }

    @GetMapping("/getBrandById/{brandId}")
    public ResponseEntity<?> getBrandById(@PathVariable Integer brandId) {
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(ResponseMapper.toBrandDTO(brand));
    }

    @PutMapping("/updateBrand/{brandId}")
    public ResponseEntity<?> updateBrand(@PathVariable Integer brandId, @RequestBody BrandDTO brandDTO) {
        Brand brand = ResponseMapper.toBrand(brandDTO);
        Brand updateBrand = brandService.updateBrand(brandId, brand);
        return ResponseEntity.ok(ResponseMapper.toBrandDTO(updateBrand));
    }
}
