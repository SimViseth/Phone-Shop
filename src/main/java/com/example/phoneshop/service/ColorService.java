package com.example.phoneshop.service;

import com.example.phoneshop.entity.Color;

public interface ColorService {
    Color create(Color color);
    Color getById(Long id);
}
