package com.ecommerce.library.services;

import com.ecommerce.library.entities.CountryEntity;

import java.util.List;

public interface ICountryService {
    List<CountryEntity> findAll();
}
