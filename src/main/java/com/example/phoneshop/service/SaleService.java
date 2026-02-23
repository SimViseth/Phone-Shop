package com.example.phoneshop.service;

import com.example.phoneshop.dto.SaleDTO;
import com.example.phoneshop.entity.Sale;

public interface SaleService {
    void sellProduct(SaleDTO saleDTO);
    Sale getById(Long saleId);
    void cancelSale(Long saleId);
}
