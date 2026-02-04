package com.example.phoneshop.repository;

import com.example.phoneshop.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    List<Model> findModelByBrandId(Integer brandId);
    List<Model> findByBrandIdAndName(Integer brandId, String name);
}
