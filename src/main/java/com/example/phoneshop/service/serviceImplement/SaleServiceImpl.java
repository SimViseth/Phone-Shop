package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.SaleDTO;
import com.example.phoneshop.dto.product.ProductSoldDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.entity.Sale;
import com.example.phoneshop.entity.SaleDetail;
import com.example.phoneshop.exception.ApiException;
import com.example.phoneshop.exception.ResourceNotFound;
import com.example.phoneshop.repository.ProductRepository;
import com.example.phoneshop.repository.SaleDetailRepository;
import com.example.phoneshop.repository.SaleRepository;
import com.example.phoneshop.service.ProductService;
import com.example.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleDetailRepository saleDetailRepository;

    @Override
    public void sellProduct(SaleDTO saleDTO) {
        // get each product
        List<Long> productIds = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();

        productIds.forEach(productService::getById);

        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));

        // validate stock
        saleDTO.getProducts().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());
            if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getProductName()));
            }
        });

        // sale
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        // record sale detail
        saleDTO.getProducts().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetail.setAmount(product.getSalePrice());
            saleDetailRepository.save(saleDetail);

            // cut stock
            Integer availableUnit = product.getAvailableUnit() - ps.getNumberOfUnit();
            product.setAvailableUnit(availableUnit);
            productRepository.save(product);
        });
    }

    @Override
    public Sale getById(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFound("Sale", saleId));
    }

    @Override
    public void cancelSale(Long saleId) {
        // update sale status
        Sale sale = getById(saleId);
        sale.setActive(false);
        saleRepository.save(sale);

        // find sale detail
        List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);

        // find product from sale detail
        List<Long> productId = saleDetails.stream()
                .map(sd -> sd.getProduct().getProductId())
                .toList();

        List<Product> products = productRepository.findAllById(productId);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));

        saleDetails.forEach(sd -> {
            Product product = sd.getProduct();
        });
    }


    private void saveSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        // sale detail
        saleDTO.getProducts().forEach(ps -> {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(null);
        });
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
