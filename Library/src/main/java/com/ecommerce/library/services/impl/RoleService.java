package com.ecommerce.library.services.impl;

import com.ecommerce.library.converters.RoleConverter;
import com.ecommerce.library.dtos.RoleDTO;
import com.ecommerce.library.repositories.IRoleRepository;
import com.ecommerce.library.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private RoleConverter roleConverter;
    @Override
    public RoleDTO findByName(String name) {
        return roleConverter.toDto(roleRepository.findByName(name));
    }
}
