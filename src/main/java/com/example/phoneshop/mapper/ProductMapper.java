package com.example.phoneshop.mapper;

import com.example.phoneshop.dto.ProductDTO;
import com.example.phoneshop.entity.Product;
import com.example.phoneshop.service.ColorService;
import com.example.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);
}
