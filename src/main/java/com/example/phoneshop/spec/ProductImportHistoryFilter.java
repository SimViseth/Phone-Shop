package com.example.phoneshop.spec;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductImportHistoryFilter {
    private LocalDate startDate;
    private LocalDate endDate;
}
