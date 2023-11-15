package com.ecommerce.library.services;

import com.ecommerce.library.dtos.UserDTO;
import com.ecommerce.library.entities.UserEntity;

public interface IUserService {
    UserDTO findByUsername(String username);
    UserEntity findByUsernameEntity(String userName);

    UserDTO save(UserDTO userDto);
    boolean isExist(Long userId);
    UserDTO findById(Long userId);
    UserDTO update(Long userId,UserDTO user);
    UserEntity changePassword(UserDTO user);
    UserDTO findByUserNameWithRoles(String username);

}
