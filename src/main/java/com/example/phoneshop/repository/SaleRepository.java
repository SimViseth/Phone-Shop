package com.example.phoneshop.repository;

import com.example.phoneshop.entity.Sale;
import com.example.phoneshop.projection.ProductSoldProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT\n" +
            "    p.product_id AS productId,\n" +
            "    p.product_name AS productName,\n" +
            "    SUM(sd.unit) AS unit,\n" +
            "    SUM(sd.unit * sd.sold_amount) AS totalAmount\n" +
            "FROM sale_details sd\n" +
            "INNER JOIN sales s ON sd.sale_id = s.sale_id\n" +
            "INNER JOIN products p ON p.product_id = sd.product_id\n" +
            "WHERE date(s.sold_date) >= :startDate AND date(s.sold_date) <= :endDate\n" +
            "GROUP BY p.product_id, p.product_name",
            nativeQuery = true)
    List<ProductSoldProjection> findProductSold(LocalDate startDate, LocalDate endDate);
}
