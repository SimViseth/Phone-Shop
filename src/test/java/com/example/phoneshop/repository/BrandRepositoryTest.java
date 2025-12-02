package com.example.phoneshop.repository;

import com.example.phoneshop.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@RequiredArgsConstructor
public interface BrandRepositoryTest {

    private final BrandRepository brandRepository;

    @Test
    public void testFindByNameLike() {
        //given
        Brand brand = new Brand();
        brand.setName("Apple");

        Brand brand2 = new Brand();
        brand2.setName("Samsung");

        brandRepository.save(brand);
        brandRepository.save(brand2);

        //when
        List<Brand> brands = brandRepository.findByNameLike("%A%");

        //then
        assertEquals(1, brands.size());
        assertEquals("Apple", brands.get(0).getName());
        assertEquals(1, brands.get(0).getId());
    }
}

