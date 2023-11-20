package com.ecommerce.library.services.impl;

import com.ecommerce.library.converters.OrderConverter;
import com.ecommerce.library.entities.*;
import com.ecommerce.library.repositories.OrderDetailRepository;
import com.ecommerce.library.repositories.OrderRepository;
import com.ecommerce.library.repositories.UserRepository;
import com.ecommerce.library.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class OrderService implements IOrderService {
     @Autowired
     private OrderRepository orderRepository;
     @Autowired
     private OrderConverter orderConverter;
     @Autowired
     private UserRepository userRepository;
     @Autowired
     private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderEntity save(ShoppingCartEntity shoppingCartEntity) {
//        OrderEntity orderEntity = new OrderEntity();
//        //find user of order
//        UserEntity userEntity = new UserEntity();
//        if (userRepository.existsById(shoppingCartEntity.getId())){
//            userEntity = userRepository.findById(shoppingCartEntity.getUser().getId()).get();
//        }
//        orderEntity.setUser(userEntity);
//        //set time order
//        long deliveryTimeInMillis = System.currentTimeMillis();
//        Date orderDate = new Date(deliveryTimeInMillis);
//        orderEntity.setOrderDate(orderDate);
//        orderEntity.setTax(10);
//        orderEntity.setAccept(false);
//        orderEntity.setPaymentMethod("Cash");
//        orderEntity.setOrderStatus("Pending");
//        orderEntity.setQuantity(shoppingCartEntity.getTotalItems());
//        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
//        for(CartItemEntity cartItemEntity : shoppingCartEntity.getCartItems()){
//            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
//            orderDetailEntity.setProduct(cartItemEntity.getProduct());
//            orderDetailEntity.setOrder(orderEntity);
//            orderDetailRepository.save(orderDetailEntity);
//            orderDetailEntities.add(orderDetailEntity);
//        }
//        orderEntity.setOrderDetailList(orderDetailEntities);
//        return orderRepository.save(orderEntity);
        return null;
    }

    @Override
    public Set<OrderEntity> findAll(String username) {
        UserEntity user = new UserEntity();
        Set<OrderEntity> orderEntities = user.getOrders();
        return orderEntities;
    }

    @Override
    public List<OrderEntity> findAllOrders() {
       return  orderRepository.findAll();

    }

    @Override
    public OrderEntity acceptOrder(Long id) {
        if (orderRepository.existsById(id)){
            OrderEntity order = orderRepository.findById(id).get();
            order.setAccept(true);
            // Assuming order is an instance of your Order class
            long deliveryTimeInMillis = System.currentTimeMillis();
            Date deliveryDate = new Date(deliveryTimeInMillis);
            order.setDeliveryDate(deliveryDate);
            return orderRepository.save(order);
        } else {
            return null;
        }

    }

    @Override
    public void cancelOrder(Long id) {
        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        }

    }
}
