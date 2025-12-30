package com.example.phoneshop.service;

import com.example.phoneshop.entity.Model;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    List<Model> getModelByBrand(Integer brandId);
}
