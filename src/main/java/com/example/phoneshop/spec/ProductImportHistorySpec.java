package com.example.phoneshop.spec;

import com.example.phoneshop.entity.ProductImportHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {

    private ProductImportHistoryFilter importFilter;

    @Override
    public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicateList = new ArrayList<>();
        if(Objects.nonNull(importFilter.getStartDate())) {
            //cb.greaterThanOrEqualTo(importHistory.get("dateImport"), importFilter.getStartDate());
            Predicate startDate = cb.greaterThanOrEqualTo(importHistory.get("dateImport"), importFilter.getStartDate());
            predicateList.add(startDate);
        }
        if(Objects.nonNull(importFilter.getEndDate())) {
            //cb.lessThanOrEqualTo(importHistory.get("dateImport"), importFilter.getEndDate());
            Predicate endDate = cb.lessThanOrEqualTo(importHistory.get("endDate"), importFilter.getEndDate());
            predicateList.add(endDate);
        }
        Predicate predicate = cb.and(predicateList.toArray(Predicate[]::new));
        return predicate;
    }
}
