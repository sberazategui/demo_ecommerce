package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Brand;
import com.ecommerce.demo.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServicesImpl implements BrandServices {

    @Autowired
    private BrandRepository brandRepository;

    public Brand getBrandById (Long id) {

        return brandRepository.getReferenceById(id);
    }
    
}
