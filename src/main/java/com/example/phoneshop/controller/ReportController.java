package com.example.phoneshop.controller;

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


}
