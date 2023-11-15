package com.ecommerce.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO{
    private Long cityId;
    private String cityName;
    private String cityCode;
    private Set<CountryDTO> countries;
}
