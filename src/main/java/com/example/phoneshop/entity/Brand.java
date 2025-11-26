package com.example.phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer id;

    @Column(name = "brand_name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}