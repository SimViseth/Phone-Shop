package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.ExpenseReportDTO;
import com.example.phoneshop.dto.ProductReportDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.entity.ProductImportHistory;
import com.example.phoneshop.entity.SaleDetail;
import com.example.phoneshop.projection.ProductSoldProjection;
import com.example.phoneshop.repository.ProductImportHistoryRepository;
import com.example.phoneshop.repository.ProductRepository;
import com.example.phoneshop.repository.SaleDetailRepository;
import com.example.phoneshop.repository.SaleRepository;
import com.example.phoneshop.service.ReportService;
import com.example.phoneshop.spec.ProductImportHistoryFilter;
import com.example.phoneshop.spec.ProductImportHistorySpec;
import com.example.phoneshop.spec.SaleDetailFilter;
import com.example.phoneshop.spec.SaleDetailSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;

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

    @Override
    public List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate) {
        ProductImportHistoryFilter importHistoryFilter = new ProductImportHistoryFilter();
        importHistoryFilter.setStartDate(startDate);
        importHistoryFilter.setEndDate(endDate);

        ProductImportHistorySpec spec = new ProductImportHistorySpec(importHistoryFilter);
        List<ProductImportHistory> importHistories = productImportHistoryRepository.findAll(spec);

        Set<Long> productIds = importHistories.stream()
                .map(his -> his.getProduct().getProductId())
                .collect(Collectors.toSet());

        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(p -> p.getProductId(), p -> p));

        Map<Product, List<ProductImportHistory>> importMap = importHistories.stream()
                .collect(Collectors.groupingBy(pi -> pi.getProduct()));

        var expenseReportDTOList = new ArrayList<ExpenseReportDTO>();

        for (var entry : importMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getProductId());
            List<ProductImportHistory> importProducts = entry.getValue();

            int totalUnit = importProducts.stream()
                    .mapToInt(pi -> pi.getImportUnit())
                    .sum();

            double totalAmount = importProducts.stream()
                    .mapToDouble(pi -> pi.getImportUnit() * pi.getPricePerUnit().doubleValue())
                    .sum();

            var expenseReportDTO = new ExpenseReportDTO();
            expenseReportDTO.setProductId(product.getProductId());
            expenseReportDTO.setProductName(product.getProductName());
            expenseReportDTO.setTotalUnit(totalUnit);
            expenseReportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
            expenseReportDTOList.add(expenseReportDTO);
        }
        Collections.sort(expenseReportDTOList, (a, b) -> (int)(a.getProductId() - b.getProductId()));

        return expenseReportDTOList;
    }
}
