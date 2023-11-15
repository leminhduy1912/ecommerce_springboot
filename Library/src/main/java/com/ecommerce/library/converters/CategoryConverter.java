package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.CategoryDTO;
import com.ecommerce.library.entities.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryEntity toEntity(CategoryEntity dto){
        CategoryEntity entity = new CategoryEntity();
        if (dto.getId() != null){
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setIsActivated(dto.getIsActivated());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }
    public CategoryDTO toDto(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        if (entity.getId() != null){
            dto.setId(entity.getId());
        }
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setIs_activated(entity.getIsActivated());
        dto.setIs_deleted(entity.getIsDeleted());
        return dto;
    }
}
