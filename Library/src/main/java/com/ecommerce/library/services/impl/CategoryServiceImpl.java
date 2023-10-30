package com.ecommerce.library.services.impl;

import com.ecommerce.library.converters.CategoryConverter;
import com.ecommerce.library.dtos.CategoryDTO;
import com.ecommerce.library.entities.CategoryEntity;
import com.ecommerce.library.repositories.CategoryRepository;
import com.ecommerce.library.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    @Override
    public List<CategoryDTO> findAllExist() {
        List<CategoryEntity> entities = categoryRepository.findAllByActivatedTrue();
        List<CategoryDTO> dtos = new ArrayList<>();
        for (CategoryEntity entity : entities){
            dtos.add(categoryConverter.toDto(entity));
        }
        return dtos;
    }
}
