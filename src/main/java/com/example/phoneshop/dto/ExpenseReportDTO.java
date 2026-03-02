package com.example.phoneshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExpenseReportDTO {
    private Long productId;
    private String productName;
    private Integer totalUnit;
    private BigDecimal totalAmount;
}
