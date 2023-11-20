package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.UserDTO;
import com.ecommerce.library.entities.RoleEntity;
import com.ecommerce.library.entities.UserEntity;
import com.ecommerce.library.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private RoleConverter roleConverter;
    @Autowired
    private ShoppingCartConverter shoppingCartConverter;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public UserDTO toDto(UserEntity entity){
        UserDTO dto = new UserDTO();

        if (entity != null){
            if (entity.getId() != null){
                dto.setId(entity.getId());
            }
            dto.setLastName(entity.getLastName());
            dto.setFirstName(entity.getFirstName());
            dto.setUsername(entity.getUsername());
            dto.setImage(entity.getImage());
            dto.setPassword(entity.getPassword());
            List<String> roleCodes = new ArrayList<>();
            for (RoleEntity roleEntity : entity.getRoles()){
                roleCodes.add(roleEntity.getCode());
            }
            dto.setRoleCodes(roleCodes);
            dto.setImage(entity.getImage());
//            dto.setShoppingCart(null);
//            if (entity.getCart() != null){
//                dto.setShoppingCart(shoppingCartConverter.toDto(entity.getCart()));
//                System.out.println("Gio hang khong trong ");
//            }
//            System.out.println("Gio hang trong ");
        }
        System.out.println("admin dto" +dto);
        return dto;
    }
    public UserEntity toEntity(UserDTO dto){

        UserEntity userEntity = new UserEntity();
        if (dto.getId() != null){
            userEntity.setId(dto.getId());
        }
        userEntity.setLastName(dto.getLastName());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setUsername(dto.getUsername());
        userEntity.setImage(dto.getImage());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (String roleCode : dto.getRoleCodes()){
            roleEntities.add(roleConverter.toEntity(roleService.findByCode(roleCode)));
            System.out.println("role find"+roleService.findByCode(roleCode));
        }

        userEntity.setRoles(roleEntities);
        userEntity.setImage(dto.getImage());
        System.out.println("admin entity" + userEntity);

        return userEntity;
    }
    public UserEntity toEntity(UserEntity userEntity,UserDTO userDto){
        userEntity.setLastName(userDto.getLastName());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setImage(userDto.getImage());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (String roleCode : userDto.getRoleCodes()){
            roleEntities.add(roleConverter.toEntity(roleService.findByCode(roleCode)));
        }
        userEntity.setRoles(roleEntities);
        System.out.println("admin entity" + userEntity);
        return userEntity;
    }
}
