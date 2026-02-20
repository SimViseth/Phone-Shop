package com.example.phoneshop.controller;

import com.example.phoneshop.dto.SaleDTO;
import com.example.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/selling")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/sale-product")
    public ResponseEntity<?> sellProduct(@RequestBody SaleDTO saleDTO) {
        saleService.sellProduct(saleDTO);
        return ResponseEntity.ok().build();
    }
}
