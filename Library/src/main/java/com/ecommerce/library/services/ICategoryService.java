package com.ecommerce.library.services;

import com.ecommerce.library.dtos.CategoryDTO;

import java.util.List;

public interface ICategoryService  {
    List<CategoryDTO> findAllExist();
}
