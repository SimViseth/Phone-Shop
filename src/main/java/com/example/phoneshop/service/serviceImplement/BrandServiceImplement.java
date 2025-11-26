package com.example.phoneshop.service.serviceImplement;

import com.example.phoneshop.dto.BrandDTO;
import com.example.phoneshop.entity.Brand;
import com.example.phoneshop.exception.ResourceNotFound;
import com.example.phoneshop.repository.BrandRepository;
import com.example.phoneshop.service.BrandService;
import com.example.phoneshop.spec.BrandFilter;
import com.example.phoneshop.spec.BrandSpec;
import com.example.phoneshop.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImplement implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImplement(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFound("Brand", brandId));
    }

    @Override
    public Brand updateBrand(Integer brandId, Brand brand) {
        Brand findBrand = getById(brandId);
        findBrand.setName(brand.getName());
        return brandRepository.save(findBrand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getBrandByName(String name) {
        return brandRepository.findByNameContaining(name);
    }

//    @Override
//    public List<Brand> getBrands(Map<String, String> params) {
//        BrandFilter brandFilter = new BrandFilter();
//
//        if(params.containsKey("name")) {
//            String name = params.get("name");
//            brandFilter.setName(name);
//        }
//
//        if(params.containsKey("id")) {
//            String id = params.get("id");
//            brandFilter.setId(Integer.parseInt(id));
//        }
//        BrandSpec brandSpec = new BrandSpec(brandFilter);
//        return brandRepository.findAll(brandSpec);
//    }
    @Override
    public Page<Brand> getBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();

        if(params.containsKey("name")) {
            String name = params.get("name");
            brandFilter.setName(name);
        }

        if(params.containsKey("id")) {
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }

        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PageUtil.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        BrandSpec brandSpec = new BrandSpec(brandFilter);

        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);

        //return brandRepository.findAll(brandSpec);
        Page<Brand> page = brandRepository.findAll(brandSpec, pageable);
        return page;
    }
}
