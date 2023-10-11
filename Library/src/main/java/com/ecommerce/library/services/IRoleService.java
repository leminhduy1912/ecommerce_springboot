package com.ecommerce.library.services;

import com.ecommerce.library.dtos.RoleDTO;

public interface IRoleService {
    RoleDTO findByName(String name);
    RoleDTO findByCode(String code);
}
