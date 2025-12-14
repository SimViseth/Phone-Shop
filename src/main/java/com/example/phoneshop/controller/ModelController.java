package com.example.phoneshop.controller;

import com.example.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/model")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
}
