package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.projection.ProductSoldProjection;
import com.example.phoneshop.repository.SaleRepository;
import com.example.phoneshop.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    @Override
    public List<ProductSoldProjection> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate, endDate);
    }
}
