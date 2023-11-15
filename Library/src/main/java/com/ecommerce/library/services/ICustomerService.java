package com.ecommerce.library.services;

import com.ecommerce.library.dtos.UserDTO;

public interface ICustomerService {
    UserDTO save(UserDTO customerDto);

    UserDTO findByUsername(String username);

    UserDTO update(UserDTO userDTO);

    UserDTO changePass(UserDTO customerDto);

    UserDTO getCustomer(String username);
}
