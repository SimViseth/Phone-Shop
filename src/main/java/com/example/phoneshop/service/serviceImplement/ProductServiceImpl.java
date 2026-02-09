package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.product.ProductImportDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.exception.ResourceNotFound;
import com.example.phoneshop.repository.ProductRepository;
import com.example.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
        product.setProductName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product", id));
    }

    @Override
    public void importProduct(ProductImportDTO importDTO) {

    }
}
