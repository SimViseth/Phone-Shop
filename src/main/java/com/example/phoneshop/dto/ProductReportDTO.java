package com.example.phoneshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductReportDTO {
    private Long productId;
    private String productName;
    private Integer unit;
    private BigDecimal totalAmount;
}
