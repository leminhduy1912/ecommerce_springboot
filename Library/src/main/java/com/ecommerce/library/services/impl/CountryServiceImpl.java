package com.ecommerce.library.services.impl;

import com.ecommerce.library.entities.CountryEntity;
import com.ecommerce.library.repositories.CountryRepository;
import com.ecommerce.library.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements ICountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Override
    public List<CountryEntity> findAll() {
        return countryRepository.findAll();
    }
}
