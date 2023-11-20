package com.ecommerce.library.services;

import com.ecommerce.library.dtos.RoleDTO;
import com.ecommerce.library.entities.RoleEntity;

public interface IRoleService {
    RoleDTO findByName(String name);
    RoleDTO findByCode(String code);

    RoleEntity save(RoleEntity role);
}
