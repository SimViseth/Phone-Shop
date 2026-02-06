package com.example.phoneshop.repository;

import com.example.phoneshop.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
