package com.ecommerce.library.converters;


import com.ecommerce.library.dtos.ProductDTO;
import com.ecommerce.library.entities.ProductEntity;
import com.ecommerce.library.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    public ProductDTO toDto(ProductEntity entity){
        ProductDTO dto = new ProductDTO();
        if (entity.getId() != null){
            dto.setId(entity.getId());
        }
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        dto.setCostPrice(entity.getCostPrice());
        dto.setCurrentQuantity(entity.getCurrentQuantity());
        dto.setSalePrice(entity.getSalePrice());
        dto.setIs_deleted(entity.getIsDeleted());
        dto.setIs_activated(entity.getIsActivated());

        dto.setCategoryName(entity.getCategory().getName());
        return dto;
    }
    public ProductEntity toEntity(ProductDTO dto){
        ProductEntity entity = new ProductEntity();
        if (dto.getId() != null){
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setCostPrice(dto.getCostPrice());
        entity.setCurrentQuantity(dto.getCurrentQuantity());
        entity.setSalePrice(dto.getSalePrice());
        entity.setIsDeleted(dto.getIs_deleted());
        entity.setIsActivated(dto.getIs_activated());
        return entity;
    }
    public ProductEntity toEntity(ProductEntity oldEntity,ProductEntity newEntity){
        if (oldEntity.getId() != null){
            newEntity.setId(oldEntity.getId());
        }
        newEntity.setName(oldEntity.getName());
        newEntity.setDescription(oldEntity.getDescription());
        newEntity.setImage(oldEntity.getImage());
        newEntity.setCostPrice(oldEntity.getCostPrice());
        newEntity.setCurrentQuantity(oldEntity.getCurrentQuantity());
        newEntity.setSalePrice(oldEntity.getSalePrice());
        newEntity.setIsDeleted(oldEntity.getIsDeleted());
        newEntity.setIsActivated(oldEntity.getIsActivated());
        return newEntity;
    }
}
