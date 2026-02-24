package com.example.phoneshop.repository;

import com.example.phoneshop.entity.Sale;
import com.example.phoneshop.projection.ProductSoldProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<ProductSoldProjection> findProductSold(LocalDate startDate, LocalDate endDate);
}
