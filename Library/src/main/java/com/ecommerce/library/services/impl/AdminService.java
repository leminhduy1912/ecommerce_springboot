package com.ecommerce.library.services.impl;


import com.ecommerce.library.converters.AdminConverter;
import com.ecommerce.library.dtos.AdminDTO;
import com.ecommerce.library.entities.AdminEntity;
import com.ecommerce.library.repositories.IAdminRepository;
import com.ecommerce.library.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IAdminRepository adminRepository;
    @Autowired
    private AdminConverter adminConverter;
        @Override
        public AdminDTO findByUsername(String username) {
            AdminEntity adminEntity = adminRepository.findByUsername(username);
            return adminConverter.toDto(adminEntity);
        }


    @Override
    public AdminDTO save(AdminDTO adminDto) {
        return null;
    }
}
