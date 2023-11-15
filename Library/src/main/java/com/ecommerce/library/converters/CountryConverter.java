package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.CountryDTO;
import com.ecommerce.library.entities.CountryEntity;
import org.springframework.stereotype.Component;

@Component
public class CountryConverter {
    public CountryDTO toDto(CountryEntity entity){
        CountryDTO dto = new CountryDTO();
        if (entity.getId() != null){
            dto.setCountryId(entity.getId());
        }
        dto.setCountryCode(entity.getCode());
        dto.setCountryName(entity.getName());
        return dto;
    }
    private CountryEntity toEntity(CountryDTO countryDTO){
        CountryEntity entity = new CountryEntity();
        if (entity.getId() != null){
            entity.setId(countryDTO.getCountryId());
        }
        entity.setName(countryDTO.getCountryName());
        entity.setCode(countryDTO.getCountryCode());
        return entity;
    }
}
