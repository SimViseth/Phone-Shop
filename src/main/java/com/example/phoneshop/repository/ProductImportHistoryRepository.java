package com.example.phoneshop.repository;

import com.example.phoneshop.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Integer> {
}
