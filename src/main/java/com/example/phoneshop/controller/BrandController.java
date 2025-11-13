package com.example.phoneshop.controller;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;
import com.example.phoneshop.mapper.BrandMapper;
import com.example.phoneshop.mapper.ResponseMapper;
import com.example.phoneshop.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/create-brand")
    public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO) {
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.createBrand(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toDTO(brand));
    }

    @GetMapping("/getBrandById/{brandId}")
    public ResponseEntity<?> getBrandById(@PathVariable Integer brandId) {
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toDTO(brand));
    }

    @PutMapping("/updateBrand/{brandId}")
    public ResponseEntity<?> updateBrand(@PathVariable Integer brandId, @RequestBody BrandDTO brandDTO) {
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        Brand updateBrand = brandService.updateBrand(brandId, brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toDTO(updateBrand));
    }

    @GetMapping
    public ResponseEntity<?> getAllBrands() {
        List<BrandDTO> list = brandService.getAllBrands()
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toDTO(brand))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getBrandByName(@RequestParam("name") String name) {
        List<BrandDTO> brandDTOList = brandService.getBrandByName(name)
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toDTO(brand))
                .collect(Collectors.toList());
        return ResponseEntity.ok(brandDTOList);
    }
}
