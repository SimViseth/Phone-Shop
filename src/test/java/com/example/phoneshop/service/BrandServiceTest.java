package com.example.phoneshop.service;

import com.example.phoneshop.entity.Brand;
import com.example.phoneshop.exception.ResourceNotFound;
import com.example.phoneshop.repository.BrandRepository;
import com.example.phoneshop.service.serviceImplement.BrandServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;
    private BrandService brandService;

    @BeforeEach
    public void setUp() {
        brandService = new BrandServiceImplement(brandRepository);
    }

//    @Test
//    public void testCreate() {
//        // given
//        Brand brand = new Brand();
//        brand.setName("Apple");
//        brand.setId(1);
//
//        // when
//        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
//        Brand brandReturn = brandService.createBrand(new Brand());
//
//        // then
//        assertEquals(1, brandReturn.getId());
//        assertEquals("Apple", brandReturn.getName());
//    }

    // BEST PRACTICE
    @Test
    public void testCreate() {
        // given
        Brand brand = new Brand();
        brand.setName("Apple");

        // when
        brandService.createBrand(brand);

        // then
        verify(brandRepository, times(1)).save(brand);
    }

    @Test
    public void testGetByIdSuccess() {
        // given
        Brand brand = new Brand();
        brand.setName("Apple");
        brand.setId(1);

        // when
        when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
        Brand brandReturn = brandService.getById(1);

        // then
        assertEquals(1, brandReturn.getId());
        assertEquals("Apple", brandReturn.getName());
    }


    // handle throw exception in case cannot find that Id
    @Test
    public void testGetByIdThrow() {
        // given

        // when
        when(brandRepository.findById(2)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> brandService.getById(2))
                .isInstanceOf(ResourceNotFound.class)
                .hasMessage("Brand with id = 2 not found");
    }
}
