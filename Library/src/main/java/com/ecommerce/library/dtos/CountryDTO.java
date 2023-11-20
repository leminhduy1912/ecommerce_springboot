package com.ecommerce.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private String countryId;
    private String countryName;
    private String countryCode;
}
