package com.example.phoneshop.repository;

import com.example.phoneshop.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Integer>, JpaSpecificationExecutor<ProductImportHistory> {
}
