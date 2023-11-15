package com.ecommerce.library.services;

import com.ecommerce.library.entities.OrderEntity;
import com.ecommerce.library.entities.ShoppingCartEntity;

import java.util.List;
import java.util.Set;

public interface IOrderService {
    OrderEntity save(ShoppingCartEntity shoppingCartEntity);

    Set<OrderEntity> findAll(String username);

    List<OrderEntity> findAllOrders();

    OrderEntity acceptOrder(Long id);

    void cancelOrder(Long id);
}
