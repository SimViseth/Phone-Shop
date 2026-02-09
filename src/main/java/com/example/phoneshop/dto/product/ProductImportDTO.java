package com.example.phoneshop.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductImportDTO {
    private Long productId;
    private Integer importUnit;
    private BigDecimal importPrice;
    private LocalDateTime importDate;
}
