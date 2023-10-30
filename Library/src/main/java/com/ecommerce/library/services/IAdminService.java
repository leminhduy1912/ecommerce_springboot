package com.ecommerce.library.services;

import com.ecommerce.library.dtos.AdminDTO;

public interface IAdminService {
    AdminDTO findByUsername(String username);

    AdminDTO save(AdminDTO adminDto);


}
