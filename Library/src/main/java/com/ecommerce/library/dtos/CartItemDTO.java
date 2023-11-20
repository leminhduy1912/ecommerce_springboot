package com.ecommerce.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private String cardId;
    private int quantity;
    private double unitPrice;
    private String shoppingCartId;
    private ProductDTO productDTO;
}
