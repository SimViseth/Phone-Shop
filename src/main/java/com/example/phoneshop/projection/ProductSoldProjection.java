package com.example.phoneshop.projection;

import java.math.BigDecimal;

public interface ProductSoldProjection {
    // productId, productName, Unit, totalAmount
    Long getProductId();
    String getProductName();
    Integer getUnit();
    BigDecimal getTotalAmount();
}
