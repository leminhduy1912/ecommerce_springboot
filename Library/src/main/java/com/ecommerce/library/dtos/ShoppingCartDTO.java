package com.ecommerce.library.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {
    private Long shoppingCartId;
    private UserDTO user;
    private double totalPrice;
    private int totalItems;
    private Set<CartItemDTO> cartItems;
}
