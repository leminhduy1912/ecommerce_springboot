package com.ecommerce.library.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;


    private String image;


    private Collection<RoleDTO> roles;
}
