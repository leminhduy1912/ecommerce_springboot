package com.ecommerce.library.services.impl;

import com.ecommerce.library.converters.ProductConverter;
import com.ecommerce.library.dtos.CartItemDTO;
import com.ecommerce.library.dtos.ProductDTO;
import com.ecommerce.library.dtos.ShoppingCartDTO;
import com.ecommerce.library.entities.CartItemEntity;
import com.ecommerce.library.entities.ProductEntity;
import com.ecommerce.library.entities.ShoppingCartEntity;
import com.ecommerce.library.entities.UserEntity;
import com.ecommerce.library.repositories.CartItemRepository;
import com.ecommerce.library.repositories.ShoppingCartRepository;
import com.ecommerce.library.services.IShoppingCartService;
import com.ecommerce.library.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCartService implements IShoppingCartService {
    @Autowired
    private IUserService userService;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
//    @Autowired
//    private ShoppingCartConverter shoppingCartConverter;

    private CartItemEntity find(Set<CartItemEntity> cartItems, long productId) {
        if (cartItems == null) {
            return null;
        }
        CartItemEntity cartItem = null;
        for (CartItemEntity item : cartItems) {
            if (item.getProduct().getId() == productId) {
                cartItem = item;
            }
        }
        return cartItem;
    }
    private int totalItem(Set<CartItemEntity> cartItemList) {
        int totalItem = 0;
        for (CartItemEntity item : cartItemList) {
            totalItem += item.getQuantity();
        }
        return totalItem;
    }
    private double totalPrice(Set<CartItemEntity> cartItemList) {
        double totalPrice = 0.0;
        for (CartItemEntity item : cartItemList) {
            totalPrice += item.getUnitPrice() * item.getQuantity();
        }
        return totalPrice;
    }
    private int totalItemDto(Set<CartItemDTO> cartItemList) {
        int totalItem = 0;
        for (CartItemDTO item : cartItemList) {
            totalItem += item.getQuantity();
        }
        return totalItem;
    }

    private double totalPriceDto(Set<CartItemDTO> cartItemList) {
        double totalPrice = 0;
        for (CartItemDTO item : cartItemList) {
            totalPrice += item.getUnitPrice() * item.getQuantity();
        }
        return totalPrice;
    }
    private CartItemDTO findInDTO(ShoppingCartDTO shoppingCart, long productId) {
        if (shoppingCart == null) {
            return null;
        }
        CartItemDTO cartItem = null;
        for (CartItemDTO item : shoppingCart.getCartItems()) {
            if (item.getProductDTO().getId() == productId) {
                cartItem = item;
            }
        }
        return cartItem;
    }
    @Override
    public ShoppingCartEntity addItemToCart(ProductDTO productDto, int quantity, String username) {
        UserEntity user = userService.findByUsernameEntity(username);
        ShoppingCartEntity shoppingCartEntity = user.getCart();
        if (shoppingCartEntity == null){
             shoppingCartEntity = new ShoppingCartEntity();
        }
        Set<CartItemEntity> cartItemList = shoppingCartEntity.getCartItems();
        CartItemEntity cartItem = find(cartItemList, productDto.getId());
        ProductEntity productEntity = productConverter.toEntity(productDto);
        double unitPrice = productDto.getCostPrice();
        int itemQuantity = 0;
        if (cartItemList == null){
            cartItemList = new HashSet<>();

                cartItem = new CartItemEntity();
                cartItem.setProduct(productEntity);
                cartItem.setShoppingCart(shoppingCartEntity);
                cartItem.setQuantity(quantity);
                cartItem.setUnitPrice(unitPrice);

                cartItemRepository.save(cartItem);
                cartItemList.add(cartItem);

        } else {
            if (cartItem == null) {
                cartItem = new CartItemEntity();
                cartItem.setProduct(productEntity);
                cartItem.setShoppingCart(shoppingCartEntity);
                cartItem.setQuantity(quantity);
                cartItem.setUnitPrice(unitPrice);
                cartItemList.add(cartItem);
                cartItemRepository.save(cartItem);
            } else {
                itemQuantity = cartItem.getQuantity() + quantity;
                cartItem.setQuantity(itemQuantity);
                cartItemRepository.save(cartItem);
            }
        }
        shoppingCartEntity.setUser(user);
        shoppingCartEntity.setCartItems(cartItemList);
        double totalPrice = totalPrice(shoppingCartEntity.getCartItems());
        int totalItem = totalItem(shoppingCartEntity.getCartItems());
        shoppingCartEntity.setTotalItems(totalItem);
        shoppingCartEntity.setTotalPrice(totalPrice);
        return shoppingCartRepository.save(shoppingCartEntity);
    }

    @Override
    public ShoppingCartEntity updateItemInCart(ProductDTO productDto, int quantity, String username) {
        UserEntity user = userService.findByUsernameEntity(username);
        ShoppingCartEntity shoppingCartEntity = user.getCart();
        Set<CartItemEntity> cartItemEntities = shoppingCartEntity.getCartItems();
        CartItemEntity cartItemEntity = find(cartItemEntities,productDto.getId());
        cartItemEntity.setQuantity(quantity);
        cartItemRepository.save(cartItemEntity);
        shoppingCartEntity.setCartItems(cartItemEntities);
        int totalItem = totalItem(cartItemEntities);
        double totalPrice = totalPrice(cartItemEntities);
        shoppingCartEntity.setTotalItems(totalItem);
        shoppingCartEntity.setTotalPrice(totalPrice);
        return shoppingCartRepository.save(shoppingCartEntity);

    }

    @Override
    public ShoppingCartEntity removeItemImcart(ProductDTO productDTO, String username) {
        UserEntity user = userService.findByUsernameEntity(username);
        ShoppingCartEntity shoppingCartEntity = user.getCart();
        Set<CartItemEntity> cartItemEntities = shoppingCartEntity.getCartItems();
        CartItemEntity cartItemEntity = find(cartItemEntities,productDTO.getId());
        cartItemRepository.delete(cartItemEntity);
        int totalItem = totalItem(cartItemEntities);
        double totalPrice = totalPrice(cartItemEntities);
        shoppingCartEntity.setCartItems(cartItemEntities);
        shoppingCartEntity.setTotalPrice(totalPrice);
        shoppingCartEntity.setTotalItems(totalItem);
        shoppingCartRepository.save(shoppingCartEntity);
        return shoppingCartEntity;
    }

    @Override
    public ShoppingCartDTO addItemToCartSession(ShoppingCartDTO cartDto, ProductDTO productDto, int quantity) {
        if (cartDto == null) {
            cartDto = new ShoppingCartDTO();
        }
        Set<CartItemDTO> cartItemDTOList = cartDto.getCartItems();

        double unitPrice = productDto.getCostPrice();
        int itemQuantity = 0;
        //CartItemDTO cartItemDTO = findInDTO(cartDto,productDto.getId());
        if (cartItemDTOList == null) {
            cartItemDTOList = new HashSet<>();
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setProductDTO(productDto);
            itemDTO.setQuantity(quantity);
            itemDTO.setUnitPrice(unitPrice);
            //itemDTO.setShoppingCartDTO(cartDto);
            cartItemDTOList.add(itemDTO);
            System.out.println("add");
        }
        else {
            CartItemDTO cartItemDTO = findInDTO(cartDto,productDto.getId());
            if (cartItemDTO == null){
                CartItemDTO itemDTO = new CartItemDTO();
                //itemDTO.setShoppingCartDTO(cartDto);
                itemDTO.setProductDTO(productDto);
                itemDTO.setQuantity(quantity);
                itemDTO.setUnitPrice(unitPrice);
                cartItemDTOList.add(itemDTO);
                System.out.println("add");
            }else {
                itemQuantity = cartItemDTO.getQuantity() + quantity;
                cartItemDTO.setQuantity(itemQuantity);
            }
        }
        System.out.println("adding product to cart");
        cartDto.setCartItems(cartItemDTOList);
        double totalPrice = totalPriceDto(cartItemDTOList);
        int totalItem = totalItemDto(cartItemDTOList);
        cartDto.setTotalItems(totalItem);
        cartDto.setTotalPrice(totalPrice);
        System.out.println(cartDto.getTotalItems());
        System.out.println(cartDto.getTotalPrice());
        System.out.println("success");
        return cartDto;
    }

    @Override
    public ShoppingCartDTO updateItemToCartSession(ShoppingCartDTO cartDto, ProductDTO productDto, int quantity) {
        Set<CartItemDTO> cartItemDTOList = cartDto.getCartItems();
        CartItemDTO item = findInDTO(cartDto, productDto.getId());
        int itemQuantity = item.getQuantity()+quantity;
        int totalItem = totalItemDto(cartItemDTOList);
        double totalPrice = totalPriceDto(cartItemDTOList);
        item.setQuantity(itemQuantity);
        cartDto.setCartItems(cartItemDTOList);
        cartDto.setTotalItems(totalItem);
        cartDto.setTotalPrice(totalPrice);
//        if (cartItemDTOList == null){
//            cartItemDTOList = new ArrayList<>();
//        }
        System.out.println(cartDto.getTotalItems());
        return cartDto;
    }

    @Override
    public ShoppingCartDTO removeItemToCartSession(ShoppingCartDTO cartDto, ProductDTO productDto, int quantity) {
            Set<CartItemDTO> cartItemDTOs = cartDto.getCartItems();
            CartItemDTO itemDTO =  findInDTO(cartDto, productDto.getId());
            cartItemDTOs.remove(itemDTO);
            double totalPrice = totalPriceDto(cartItemDTOs);
            int totalItem = totalItemDto(cartItemDTOs);
            cartDto.setCartItems(cartItemDTOs);
            cartDto.setTotalPrice(totalPrice);
            cartDto.setTotalItems(totalItem);
            System.out.println(cartDto.getTotalItems());
            return cartDto;
    }

    @Override
    public ShoppingCartEntity combineCart(ShoppingCartDTO cartDto, ShoppingCartEntity shoppingCartEntity) {
        if (shoppingCartEntity == null){
            shoppingCartEntity = new ShoppingCartEntity();
        }
        Set<CartItemEntity> cartItemEntities = shoppingCartEntity.getCartItems();
        if (cartItemEntities == null){
            cartItemEntities = new HashSet<>();
        }
        Set<CartItemEntity>   cartItemEntitySet = convertCartItem(cartDto.getCartItems(),shoppingCartEntity);
        for (CartItemEntity cartItemEntity : cartItemEntitySet){
            cartItemEntities.add(cartItemEntity);
        }
        double totalPrice = totalPrice(cartItemEntities);
        int totalItems = totalItem(cartItemEntities);
        shoppingCartEntity.setTotalItems(totalItems);
        shoppingCartEntity.setCartItems(cartItemEntities);
        shoppingCartEntity.setTotalPrice(totalPrice);
        return shoppingCartEntity;
    }

    @Override
    public void deleteCartById(Long id) {
        ShoppingCartEntity  shoppingCart = shoppingCartRepository.getById(id);
        for (CartItemEntity item : shoppingCart.getCartItems()){
            cartItemRepository.deleteById(item.getId());
        }
        shoppingCart.setUser(null);
        shoppingCart.getCartItems().clear();
        shoppingCart.setTotalPrice(0);
        shoppingCart.setTotalItems(0);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartEntity getCartByUsername(String username) {
        UserEntity userEntity = userService.findByUsernameEntity(username);
        return userEntity.getCart();
    }

    private Set<CartItemEntity> convertCartItem(Set<CartItemDTO> cartItemDtos, ShoppingCartEntity cart) {
        Set<CartItemEntity> cartItems = new HashSet<>();
        for (CartItemDTO cartItemDto : cartItemDtos) {
            CartItemEntity cartItem = new CartItemEntity();
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setProduct(productConverter.toEntity(cartItemDto.getProductDTO()));
            cartItem.setUnitPrice(cartItemDto.getUnitPrice());
            cartItem.setId(cartItemDto.getCardId());
            cartItem.setShoppingCart(cart);
            cartItems.add(cartItem);
        }
        return cartItems;
    }


}
