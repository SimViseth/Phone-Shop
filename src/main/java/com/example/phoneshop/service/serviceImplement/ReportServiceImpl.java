package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.ProductReportDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.entity.SaleDetail;
import com.example.phoneshop.projection.ProductSoldProjection;
import com.example.phoneshop.repository.ProductRepository;
import com.example.phoneshop.repository.SaleDetailRepository;
import com.example.phoneshop.repository.SaleRepository;
import com.example.phoneshop.service.ReportService;
import com.example.phoneshop.spec.SaleDetailFilter;
import com.example.phoneshop.spec.SaleDetailSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;

    // Option 1: use raw Query
    @Override
    public List<ProductSoldProjection> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate, endDate);
    }

    // Option2: use JpaSpecification
    @Override
    public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {

        List<ProductReportDTO> list = new ArrayList<>();

        SaleDetailFilter saleDetailFilter = new SaleDetailFilter();
        saleDetailFilter.setStartDate(startDate);
        saleDetailFilter.setEndDate(endDate);
        Specification<SaleDetail> spec = new SaleDetailSpec(saleDetailFilter);
        List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);

        List<Long> productIds = saleDetails.stream()
                .map(sd -> sd.getProduct().getProductId())
                .toList();

        Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));

        Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
                .collect(Collectors.groupingBy(SaleDetail::getProduct));

        for(var entry: saleDetailMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getProductId());
            List<SaleDetail> sdList = entry.getValue();

            //total unit
            Integer unit = sdList.stream().map(SaleDetail::getUnit).reduce(0, (a,b) -> a+b);
            double totalAmount = sdList.stream()
                    .mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue())
                    .sum();

            ProductReportDTO reportDTO = new ProductReportDTO();
            reportDTO.setProductId(product.getProductId());
            reportDTO.setProductName(product.getProductName());
            reportDTO.setUnit(unit);
            reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
            list.add(reportDTO);
        }

        return list;
    }
}
