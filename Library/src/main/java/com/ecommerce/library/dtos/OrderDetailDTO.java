package com.ecommerce.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Long orderDetailId;
    private Long orderId;
    private String  productName;
    private Long productId;
}
