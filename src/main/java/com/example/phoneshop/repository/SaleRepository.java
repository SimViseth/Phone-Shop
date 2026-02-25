package com.example.phoneshop.repository;

import com.example.phoneshop.entity.Sale;
import com.example.phoneshop.projection.ProductSoldProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = """
            SELECT
                p.product_id AS productId,
                p.product_name AS productName,
                SUM(sd.unit) AS unit,
                SUM(sd.unit * sd.sold_amount) AS totalAmount
            FROM sale_details sd
            INNER JOIN sales s ON sd.sale_id = s.sale_id
            INNER JOIN products p ON p.product_id = sd.product_id
            WHERE date(s.sold_date) >= :startDate AND date(s.sold_date) <= :endDate
            GROUP BY p.product_id, p.product_name
            """,
            nativeQuery = true)
    List<ProductSoldProjection> findProductSold(LocalDate startDate, LocalDate endDate);
}
