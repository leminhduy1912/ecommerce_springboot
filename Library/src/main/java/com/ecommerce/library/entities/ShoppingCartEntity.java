package com.ecommerce.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@Entity
@Table(name = "shopping_carts")
public class ShoppingCartEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "total_items")
    private int totalItems;

    @OneToMany(mappedBy = "shoppingCart",cascade = CascadeType.ALL)
    private Set<CartItemEntity> cartItems;
    public ShoppingCartEntity() {
        this.cartItems = new HashSet<>();
        this.totalItems = 0;
        this.totalPrice = 0.0;
    }

}
