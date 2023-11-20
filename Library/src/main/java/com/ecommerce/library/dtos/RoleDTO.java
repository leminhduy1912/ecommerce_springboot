package com.ecommerce.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private String id;
    private String name;
    private String code;
}
