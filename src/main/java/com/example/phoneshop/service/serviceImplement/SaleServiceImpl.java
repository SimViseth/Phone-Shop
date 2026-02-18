package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.SaleDTO;
import com.example.phoneshop.dto.product.ProductImportDTO;
import com.example.phoneshop.dto.product.ProductSoldDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.repository.SaleRepository;
import com.example.phoneshop.service.ProductService;
import com.example.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductService productService;

    @Override
    public void sellProduct(SaleDTO saleDTO) {
        // validate


        // save
    }

    // validate
    private void validate(SaleDTO saleDTO) {

        // ------- VALIDATE PRODUCT -----------

        /* Option 1
            List<Long> productId = saleDTO.getProducts().stream()
                    .map(ProductSoldDTO::getProductId)
                    .toList();
         */

        // Option 2
        saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .forEach(productService::getById); // = forEach(productId -> productService.getById(productId));


        // ------- VALIDATE STOCK ------------


    }
}
