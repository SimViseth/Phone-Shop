package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.product.ProductImportDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.entity.ProductImportHistory;
import com.example.phoneshop.exception.ResourceNotFound;
import com.example.phoneshop.mapper.ProductMapper;
import com.example.phoneshop.repository.ProductImportHistoryRepository;
import com.example.phoneshop.repository.ProductRepository;
import com.example.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

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
        Product product = getById(importDTO.getProductId());
        Integer availableUnit = product.getAvailableUnit() + importDTO.getImportUnit();
        product.setAvailableUnit(availableUnit);
        productRepository.save(product);

        ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
        productImportHistoryRepository.save(importHistory);
    }
}
