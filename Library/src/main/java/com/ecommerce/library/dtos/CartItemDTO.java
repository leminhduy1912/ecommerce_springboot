package com.ecommerce.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long cardId;
    private int quantity;
    private double unitPrice;
    private Long shoppingCartId;
    private ProductDTO productDTO;
}
