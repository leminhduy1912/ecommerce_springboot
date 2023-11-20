package com.ecommerce.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{
    @Id
    @Column(name = "order_id")
    private String id;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "tax")
    private double tax;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "is_accept")
    private boolean isAccept;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetailEntity> orderDetailList;




}