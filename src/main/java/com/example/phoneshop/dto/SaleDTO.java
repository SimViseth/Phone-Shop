package com.example.phoneshop.dto;

import com.example.phoneshop.dto.product.ProductSoldDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SaleDTO {
    private List<ProductSoldDTO> products;
    private LocalDateTime saleDate;
}
