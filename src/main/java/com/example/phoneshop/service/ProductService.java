package com.example.phoneshop.service;

import com.example.phoneshop.dto.product.ProductImportDTO;
import com.example.phoneshop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product createProduct(Product product);
    Product getById(Long id);
    Product getByModelIdAndColorId(Long modelId, Long colorId);
    void importProduct(ProductImportDTO importDTO);
    void setSalePrice(Long productId, BigDecimal price);

    List<Product> getAllProducts();
    Map<Integer, String> uploadProduct(MultipartFile file);
}
