package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.repository.SaleRepository;
import com.example.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;


}
