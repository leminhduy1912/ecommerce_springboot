package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.CartItemDTO;
import com.ecommerce.library.entities.CartItemEntity;
import com.ecommerce.library.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
    @Autowired
    private IProductService productService;
    @Autowired
    private ProductConverter productConverter;
    public CartItemDTO toDto(CartItemEntity entity){
        CartItemDTO dto = new CartItemDTO();
        if (entity.getId() != null){
            dto.setCardId(entity.getId());
        }
        dto.setQuantity(entity.getQuantity());
        dto.setProductDTO(productConverter.toDto(entity.getProduct()));
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setShoppingCartId(entity.getShoppingCart().getId());
        return dto;
    }
    public CartItemEntity toEntity(CartItemDTO dto){
        CartItemEntity cartItemEntity = new CartItemEntity();
        if (dto.getCardId() != null){
            cartItemEntity.setId(dto.getCardId());
        }
        cartItemEntity.setUnitPrice(dto.getUnitPrice());
        cartItemEntity.setQuantity(dto.getQuantity());
        cartItemEntity.setProduct(productConverter.toEntity(dto.getProductDTO()));

        return cartItemEntity;
    }
}
