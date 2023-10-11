package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.AdminDTO;
import com.ecommerce.library.dtos.RoleDTO;
import com.ecommerce.library.entities.AdminEntity;
import com.ecommerce.library.entities.RoleEntity;
import com.ecommerce.library.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AdminConverter {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private RoleConverter roleConverter;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public AdminDTO toDto(AdminEntity entity){
        AdminDTO dto = new AdminDTO();

        if (entity != null){
            if (entity.getId() != null){
                dto.setId(entity.getId());
            }
            dto.setLastName(entity.getLastName());
            dto.setFirstName(entity.getFirstName());
            dto.setUsername(entity.getUsername());
            dto.setImage(entity.getImage());
            List<String> roleCodes = new ArrayList<>();
            for (RoleEntity roleEntity : entity.getRoles()){
                roleCodes.add(roleEntity.getCode());
            }
            dto.setRoleCodes(roleCodes);
            dto.setImage(entity.getImage());
        }
        return dto;
    }
    public AdminEntity toEntity(AdminDTO dto){

        AdminEntity adminEntity = new AdminEntity();
        if (dto.getId() != null){
            adminEntity.setId(dto.getId());
        }
        adminEntity.setLastName(dto.getLastName());
        adminEntity.setFirstName(dto.getFirstName());
        adminEntity.setUsername(dto.getUsername());
        adminEntity.setImage(dto.getImage());
        adminEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (String roleCode : dto.getRoleCodes()){
             roleEntities.add(roleConverter.toEntity(roleService.findByCode(roleCode)));
        }
        adminEntity.setRoles(roleEntities);
        adminEntity.setImage(dto.getImage());

        return adminEntity;
    }
}
