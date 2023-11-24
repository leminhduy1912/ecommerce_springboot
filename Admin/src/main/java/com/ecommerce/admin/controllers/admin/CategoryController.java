package com.ecommerce.admin.controllers.admin;

import com.ecommerce.library.dtos.CategoryDTO;
import com.ecommerce.library.dtos.ResponseObject;
import com.ecommerce.library.entities.CategoryEntity;
import com.ecommerce.library.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @PostMapping("/api/v1/category")
    public ResponseEntity<ResponseObject> addCategory(@RequestBody CategoryDTO category){
        System.out.println("name"+ category.getName());
       return  ResponseEntity.status(HttpStatus.OK).body(
                new  ResponseObject(200,"Added successfully",categoryService.addCategory(category))
       );
    }
}
