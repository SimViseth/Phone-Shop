package com.example.phoneshop.service;

import com.example.phoneshop.dto.product.PriceDTO;
import com.example.phoneshop.dto.product.ProductImportDTO;
import com.example.phoneshop.entity.Product;

import java.math.BigDecimal;

public interface ProductService {
    Product createProduct(Product product);
    Product getById(Long id);
    void importProduct(ProductImportDTO importDTO);
    void setSalePrice(Long productId, BigDecimal price);
}
