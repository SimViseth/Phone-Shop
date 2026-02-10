package com.example.phoneshop.controller;

import com.example.phoneshop.dto.product.ProductDTO;
import com.example.phoneshop.dto.product.ProductImportDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.mapper.ProductMapper;
import com.example.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        product = productService.createProduct(product);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/import-product")
    public ResponseEntity<?> importProduct(@RequestBody ProductImportDTO importDTO) {

    }
}
