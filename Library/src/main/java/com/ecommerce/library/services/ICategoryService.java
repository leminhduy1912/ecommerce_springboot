package com.ecommerce.library.services;

import com.ecommerce.library.dtos.CategoryDTO;
import com.ecommerce.library.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryService  {
    List<CategoryDTO> findAllExist();
}
