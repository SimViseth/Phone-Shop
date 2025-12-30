package com.example.phoneshop.controller;

import com.example.phoneshop.dto.ModelDTO;
import com.example.phoneshop.entity.Model;
import com.example.phoneshop.mapper.ModelEntityMapper;
import com.example.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/model")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
    private final ModelEntityMapper modelEntityMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO) {
        Model model = modelEntityMapper.toModel(modelDTO);
        model = modelService.save(model);
        return ResponseEntity.ok(modelEntityMapper.toModelDTO(model));
    }

    @GetMapping("/getModelByBrand/{brandId}")
    public ResponseEntity<List<ModelDTO>> getModelByBrand(@PathVariable Integer brandId) {
        List<Model> brands = modelService.getModelByBrand(brandId);
        List<ModelDTO> list = brands.stream()
                .map(modelEntityMapper::toModelDTO)
                .toList();
        return ResponseEntity.ok(list);
    }
}
