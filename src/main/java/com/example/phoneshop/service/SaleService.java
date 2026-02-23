package com.example.phoneshop.service;

import com.example.phoneshop.dto.SaleDTO;

public interface SaleService {
    void sellProduct(SaleDTO saleDTO);
    void cancelSale(Long saleId);
}
