package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.repository.ColorRepository;
import com.example.phoneshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
}
