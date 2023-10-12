package com.ecommerce.library.services.impl;


import com.ecommerce.library.converters.AdminConverter;
import com.ecommerce.library.dtos.AdminDTO;
import com.ecommerce.library.entities.AdminEntity;
import com.ecommerce.library.repositories.IAdminRepository;
import com.ecommerce.library.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private IAdminRepository adminRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AdminConverter adminConverter;
        @Override
        @Transactional
        public AdminDTO findByUsername(String username) {
            System.out.println("tim thay admin" + username);
            AdminEntity adminEntity = adminRepository.findByUsername(username);

            return adminConverter.toDto(adminEntity);
        }


    @Override
    @Transactional
    public AdminDTO save(AdminDTO adminDto) {
            System.out.println("saving" + adminDto);

        return adminConverter.toDto(adminRepository.save(adminConverter.toEntity(adminDto)));
    }
}
