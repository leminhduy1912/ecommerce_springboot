package com.ecommerce.library.services;

import com.ecommerce.library.dtos.ProductDTO;
import com.ecommerce.library.dtos.ShoppingCartDTO;
import com.ecommerce.library.entities.ShoppingCartEntity;

public interface IShoppingCartService {
    ShoppingCartEntity addItemToCart(ProductDTO productDto, int quantity, String username);
    ShoppingCartEntity updateItemInCart(ProductDTO productDto, int quantity, String username);
    ShoppingCartEntity removeItemImcart(ProductDTO productDTO,String username);
    ShoppingCartDTO addItemToCartSession(ShoppingCartDTO cartDto, ProductDTO productDto, int quantity);
    ShoppingCartDTO updateItemToCartSession(ShoppingCartDTO cartDto, ProductDTO productDto, int quantity);
    ShoppingCartDTO removeItemToCartSession(ShoppingCartDTO cartDto, ProductDTO productDto, int quantity);
    ShoppingCartEntity combineCart(ShoppingCartDTO cartDto, ShoppingCartEntity shoppingCartEntity);
    public void deleteCartById(Long id);
    ShoppingCartEntity getCartByUsername(String username);
}
