package com.example.phoneshop.service;

import com.example.phoneshop.entity.Brand;
import com.example.phoneshop.repository.BrandRepository;
import com.example.phoneshop.service.serviceImplement.BrandServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;
    private BrandService brandService;

    @BeforeEach
    public void setUp() {
        brandService = new BrandServiceImplement(brandRepository);
    }

    @Test
    public void testCreate() {
        // given
        Brand brand = new Brand();
        brand.setName("Apple");
        brand.setId(1);

        // when
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        Brand brandReturn = brandService.createBrand(new Brand());

        // then
        assertEquals(1, brandReturn.getId());
        assertEquals("Apple", brandReturn.getName());
    }
}
