package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.RoleDTO;
import com.ecommerce.library.entities.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
        public RoleDTO toDto(RoleEntity roleEntity){
            RoleDTO dto = new RoleDTO();
            dto.setId(roleEntity.getId());
            dto.setName(roleEntity.getName());
            return dto;
        }
}
