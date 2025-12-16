package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.entity.Model;
import com.example.phoneshop.repository.ModelRepository;
import com.example.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelServiceImplement implements ModelService {

    private final ModelRepository modelRepository;
    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }
}
