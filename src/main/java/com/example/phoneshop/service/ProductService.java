package com.example.phoneshop.service;

import com.example.phoneshop.dto.product.PriceDTO;
import com.example.phoneshop.dto.product.ProductDTO;
import com.example.phoneshop.dto.product.ProductImportDTO;
import com.example.phoneshop.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getById(Long id);
    void importProduct(ProductImportDTO importDTO);
    void setSalePrice(Long productId, BigDecimal price);

    List<Product> getAllProducts();
    void uploadProduct(MultipartFile file);
}
