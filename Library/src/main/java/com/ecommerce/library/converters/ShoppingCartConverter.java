package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.CartItemDTO;
import com.ecommerce.library.dtos.ShoppingCartDTO;
import com.ecommerce.library.dtos.UserDTO;
import com.ecommerce.library.entities.CartItemEntity;
import com.ecommerce.library.entities.ShoppingCartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class ShoppingCartConverter {

   @Autowired
    private ProductConverter productConverter;
    public ShoppingCartDTO toDto(ShoppingCartEntity entity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(entity.getUser().getUsername());
        ShoppingCartDTO dto = new ShoppingCartDTO();
        if (entity.getId() != null){
            dto.setShoppingCartId(entity.getId());
        }
        dto.setUser(userDTO);
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setTotalItems(entity.getTotalItems());
        Set<CartItemDTO> cartItemDtos = new HashSet<>();
        for (CartItemEntity cartItemEntity : entity.getCartItems()){
              CartItemDTO cartItemDTO = new CartItemDTO();
              cartItemDTO.setProductDTO(productConverter.toDto(cartItemEntity.getProduct()));
              cartItemDTO.setUnitPrice(cartItemEntity.getUnitPrice());
              cartItemDTO.setQuantity(cartItemEntity.getQuantity());
              cartItemDTO.setCardId(cartItemEntity.getId());
              cartItemDTO.setShoppingCartId(cartItemEntity.getShoppingCart().getId());
              cartItemDtos.add(cartItemDTO);
        }
        dto.setCartItems(cartItemDtos);
        return dto;
    }
}
