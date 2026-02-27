package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.ProductReportDTO;
import com.example.phoneshop.entity.SaleDetail;
import com.example.phoneshop.projection.ProductSoldProjection;
import com.example.phoneshop.repository.SaleDetailRepository;
import com.example.phoneshop.repository.SaleRepository;
import com.example.phoneshop.service.ReportService;
import com.example.phoneshop.spec.SaleDetailFilter;
import com.example.phoneshop.spec.SaleDetailSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    // 0ption 1: use raw Query
    @Override
    public List<ProductSoldProjection> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate, endDate);
    }

    // Option2: use JpaSpecification
    @Override
    public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
        SaleDetailFilter saleDetailFilter = new SaleDetailFilter();
        saleDetailFilter.setStartDate(startDate);
        saleDetailFilter.setEndDate(endDate);
        Specification<SaleDetail> spec = new SaleDetailSpec(saleDetailFilter);
        List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);
        saleDetails.stream()
                .collect(Collectors.groupingBy(SaleDetail::getProduct));
        return null;
    }
}
