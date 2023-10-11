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
            dto.setCode(roleEntity.getCode());
            return dto;
        }
    public RoleEntity toEntity(RoleDTO roleDTO){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleDTO.getId());
        roleEntity.setName(roleDTO.getName());
        roleEntity.setCode(roleDTO.getCode());
        return roleEntity;
    }
}
