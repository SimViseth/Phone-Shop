package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.repository.ProductRepository;
import com.example.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
}
