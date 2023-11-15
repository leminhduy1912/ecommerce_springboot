package com.ecommerce.library.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String image;

    private List<String> roleCodes;
    private ShoppingCartDTO shoppingCart;
}
