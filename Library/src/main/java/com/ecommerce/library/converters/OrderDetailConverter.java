package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.OrderDetailDTO;
import com.ecommerce.library.entities.OrderDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailConverter {
    public OrderDetailDTO toDto(OrderDetailEntity entity){
        OrderDetailDTO dto = new OrderDetailDTO();
        if (entity.getId()!= null){
            dto.setOrderDetailId(entity.getId());
        }
        dto.setProductName(entity.getProduct().getName());
        dto.setOrderId(entity.getOrder().getId());
        return dto;

    }
    public OrderDetailEntity toEntity(OrderDetailDTO dto){
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        if (dto.getOrderDetailId() != null){
            orderDetailEntity.setId(dto.getOrderId());
        }
        //orderDetailEntity.setProduct(dto.getProductId());
        //
        return  orderDetailEntity;

    }
}
