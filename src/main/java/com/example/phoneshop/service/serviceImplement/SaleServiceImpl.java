package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.SaleDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.entity.Sale;
import com.example.phoneshop.exception.ApiException;
import com.example.phoneshop.repository.ProductRepository;
import com.example.phoneshop.repository.SaleDetailRepository;
import com.example.phoneshop.repository.SaleRepository;
import com.example.phoneshop.service.ProductService;
import com.example.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleDetailRepository saleDetailRepository;

    @Override
    public void sellProduct(SaleDTO saleDTO) {
        // validate
        validate(saleDTO);

        // save
    }

    private void saveSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);
    }

    // validate
    private void validate(SaleDTO saleDTO) {

        // ------- VALIDATE PRODUCT -----------

        /* Option 1
            List<Long> productId = saleDTO.getProducts().stream()
                    .map(ProductSoldDTO::getProductId)
                    .toList();
         */

        /* Option 2
        saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .forEach(productService::getById); // = forEach(productId -> productService.getById(productId));
        */

        saleDTO.getProducts().forEach(ps -> {
            Product product = productService.getById(ps.getProductId());
            if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock");
            }
        });

    }
}
