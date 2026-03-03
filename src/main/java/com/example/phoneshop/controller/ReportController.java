package com.example.phoneshop.controller;

import com.example.phoneshop.dto.ExpenseReportDTO;
import com.example.phoneshop.dto.ProductReportDTO;
import com.example.phoneshop.projection.ProductSoldProjection;
import com.example.phoneshop.service.ReportService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity<?> getReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate,
                                       @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate)
    {
        List<ProductSoldProjection> productSoldList = reportService.getProductSold(startDate, endDate);
        return ResponseEntity.ok(productSoldList);
    }

    @GetMapping("v2/{startDate}/{endDate}")
    public ResponseEntity<?> productSoldV2(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate) {
        List<ProductReportDTO> productSolds = reportService.getProductReport(startDate, endDate);
        return ResponseEntity.ok(productSolds);
    }

    @GetMapping("expense/{startDate}/{endDate}")
    public ResponseEntity<?> expenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
        List<ExpenseReportDTO> expenseReportDTOs = reportService.getExpenseReport(startDate, endDate);
        return ResponseEntity.ok(expenseReportDTOs);
    }
}
