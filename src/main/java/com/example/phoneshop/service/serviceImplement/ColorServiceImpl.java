package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.entity.Color;
import com.example.phoneshop.exception.ResourceNotFound;
import com.example.phoneshop.repository.ColorRepository;
import com.example.phoneshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public Color create(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Color", id));
    }
}
