package com.ecommerce.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEntity extends BaseEntity {
    @Id
    @Column(name = "cart_item_id")
    private String id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "unit_price")
    private double unitPrice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCartEntity shoppingCart;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductEntity product;
}