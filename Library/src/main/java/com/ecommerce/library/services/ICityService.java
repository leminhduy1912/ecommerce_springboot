package com.ecommerce.library.services;

import com.ecommerce.library.entities.CityEntity;

import java.util.List;

public interface ICityService {
    List<CityEntity> findAll();
}
