package com.example.phoneshop.service;

import com.example.phoneshop.dto.ProductImportDTO;
import com.example.phoneshop.entity.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product getById(Long id);
    void importProduct(ProductImportDTO importDTO);
}
