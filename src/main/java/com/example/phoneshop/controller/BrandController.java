package com.example.phoneshop.controller;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;
import com.example.phoneshop.mapper.BrandMapper;
import com.example.phoneshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

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

    // Use Map: so can input param as much as possible without limitation
    @GetMapping("getByMultiParams")
    public ResponseEntity<?> getBrandsByMultiParams(@RequestParam Map<String, String> params) {
        List<BrandDTO> list = brandService.getBrands(params)
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toDTO(brand))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

//    @GetMapping("/getByName")
//    public ResponseEntity<?> getBrandByName(@RequestParam("name") String name) {
//        List<BrandDTO> brandDTOList = brandService.getBrandByName(name)
//                .stream()
//                .map(brand -> BrandMapper.INSTANCE.toDTO(brand))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(brandDTOList);
//    }
}
