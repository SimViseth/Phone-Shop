package com.example.phoneshop.service;

import com.example.phoneshop.dto.ExpenseReportDTO;
import com.example.phoneshop.dto.ProductReportDTO;
import com.example.phoneshop.projection.ProductSoldProjection;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ProductSoldProjection> getProductSold(LocalDate startDate, LocalDate endDate);
    List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate);
    List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate);
}
