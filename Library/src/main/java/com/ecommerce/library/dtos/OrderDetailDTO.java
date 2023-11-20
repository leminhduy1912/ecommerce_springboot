package com.ecommerce.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private String orderDetailId;
    private String orderId;
    private String  productName;
    private String productId;
}
