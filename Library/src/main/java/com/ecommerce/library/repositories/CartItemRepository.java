package com.ecommerce.library.repositories;

import com.ecommerce.library.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {
    @Query(value = "UPDATE cart_items set shopping_cart_id = null where shopping_cart_id = ?1", nativeQuery = true)
    void deleteCartItemById(Long cartId);
}
