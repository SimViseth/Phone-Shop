package com.example.phoneshop.repository;

import com.example.phoneshop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {

    List<Brand> findByNameLike(String name);
    List<Brand> findByNameContaining(String name);
}
