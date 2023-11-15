package com.ecommerce.library.converters;

import com.ecommerce.library.dtos.OrderDTO;
import com.ecommerce.library.dtos.OrderDetailDTO;
import com.ecommerce.library.entities.OrderDetailEntity;
import com.ecommerce.library.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {
    @Autowired
    private OrderDetailConverter orderDetailConverter;
    public OrderDTO toDto(OrderEntity entity){
        OrderDTO dto = new OrderDTO();
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        if(entity.getOrderDetailList() != null){
            for(OrderDetailEntity orderDetailEntity : entity.getOrderDetailList()){
                orderDetailDTOList.add(orderDetailConverter.toDto(orderDetailEntity));
            }
        }

        if (entity.getId() != null){
            dto.setOrderId(entity.getId());
        }
        dto.setOrderDate(entity.getOrderDate());
        dto.setDeliveryDate(entity.getDeliveryDate());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setTax(entity.getTax());
        dto.setQuantity(entity.getQuantity());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setAccept(entity.isAccept());
        dto.setUserId(entity.getUser().getId());
        dto.setOrderDetails(orderDetailDTOList);
        return dto;
    }
}
