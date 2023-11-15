package com.ecommerce.library.services.impl;

import com.ecommerce.library.entities.CityEntity;
import com.ecommerce.library.repositories.CityRepository;
import com.ecommerce.library.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private CityRepository cityRepository;
    @Override
    public List<CityEntity> findAll() {
        return cityRepository.findAll();
    }
}
