package com.example.phoneshop.controller;

import com.example.phoneshop.dto.ColorDTO;
import com.example.phoneshop.entity.Color;
import com.example.phoneshop.mapper.ColorMapper;
import com.example.phoneshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @PostMapping("/create-color")
    public ResponseEntity<?> createColor(@RequestBody ColorDTO colorDTO) {
        Color color = ColorMapper.INSTANCE.toColor(colorDTO);
        color = colorService.create(color);
        return ResponseEntity.ok(ColorMapper.INSTANCE.toColorDTO(color));
    }

    @GetMapping("/{colorId}")
    public ResponseEntity<?> getColorById(@PathVariable Long colorId) {
        Color color = colorService.getById(colorId);
        return ResponseEntity.ok(ColorMapper.INSTANCE.toColorDTO(color));
    }
}
