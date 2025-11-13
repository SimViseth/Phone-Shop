package com.example.phoneshop.repository;

import com.example.phoneshop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    List<Brand> findByNameContaining(String name);
}
