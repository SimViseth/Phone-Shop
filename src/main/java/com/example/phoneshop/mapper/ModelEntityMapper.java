package com.example.phoneshop.mapper;

import com.example.phoneshop.dto.ModelDTO;
import com.example.phoneshop.entity.Model;
import com.example.phoneshop.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelEntityMapper {
    ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);

    @Mapping(target = "brand", source = "brandId")
    Model toModel(ModelDTO dto);

    @Mapping(target = "brandId", source = "brand.id")
    ModelDTO toModelDTO(Model model);
}
