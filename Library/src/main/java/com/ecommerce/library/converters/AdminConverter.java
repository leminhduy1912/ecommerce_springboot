package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.AdminDTO;
import com.ecommerce.library.entities.AdminEntity;
import com.ecommerce.library.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class AdminConverter {
    @Autowired
    private IRoleService roleService;
    public AdminDTO toDto(AdminEntity entity){
        AdminDTO dto = new AdminDTO();
        dto.setId(entity.getId());
        dto.setLastName(entity.getLastName());
        dto.setFirstName(entity.getFirstName());
        dto.setRoles(Arrays.asList(roleService.findByName("ADMIN")));
        dto.setImage(entity.getImage());
        return dto;
    }
}
