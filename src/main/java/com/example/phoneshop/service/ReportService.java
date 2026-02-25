package com.example.phoneshop.service;

import com.example.phoneshop.projection.ProductSoldProjection;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ProductSoldProjection> getProductSold(LocalDate startDate, LocalDate endDate);
}
