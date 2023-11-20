package com.ecommerce.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO{
    private String cityId;
    private String cityName;
    private String cityCode;
    private Set<CountryDTO> countries;
}
