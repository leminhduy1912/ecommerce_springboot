package com.ecommerce.library.services.impl;


import com.ecommerce.library.converters.UserConverter;
import com.ecommerce.library.dtos.UserDTO;
import com.ecommerce.library.entities.UserEntity;
import com.ecommerce.library.repositories.UserRepository;
import com.ecommerce.library.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserConverter userConverter;
        @Override
        @Transactional
        public UserDTO findByUsername(String username) {

            UserEntity userEntity = userRepository.findByUsername(username);
            return userConverter.toDto(userEntity);
        }

    @Override
    public UserEntity findByUsernameEntity(String userName) {
            UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity.getId() != null){
            return userEntity;
        } else {
            return null;
        }
    }


    @Override
    @Transactional
    public UserDTO save(UserDTO adminDto) {
            System.out.println("saving" + adminDto);

        return userConverter.toDto(userRepository.save(userConverter.toEntity(adminDto)));
    }

    @Override
    public boolean isExist(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public UserDTO findById(Long userId) {
            boolean isExist = isExist(userId);
            if (isExist){
                UserEntity userEntity = userRepository.getById(userId);
                UserDTO userDTO = userConverter.toDto(userEntity);
                return userDTO;
            } else {
                return null;
            }
    }

    @Override
    public UserDTO update(Long userId,UserDTO user) {
            UserEntity userEntityOld = userRepository.getById(userId);
            UserEntity userEntity = userConverter.toEntity(userEntityOld,user);
        return userConverter.toDto(userRepository.save(userEntity));
    }

    @Override
    public UserEntity changePassword(UserDTO userDto) {
        UserEntity user = userRepository.findByUsername(userDto.getUsername());
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public UserDTO findByUserNameWithRoles(String username) {
            UserEntity entity = userRepository.findByUserNameWithRoles(username);
        return userConverter.toDto(entity);
    }


}
