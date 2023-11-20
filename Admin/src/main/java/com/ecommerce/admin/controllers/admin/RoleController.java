package com.ecommerce.admin.controllers.admin;

import com.ecommerce.library.entities.RoleEntity;
import com.ecommerce.library.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @PostMapping("api/v1/role")
    public RoleEntity addRole(@RequestBody RoleEntity roleEntity){
        System.out.println(roleEntity);
        return roleService.save(roleEntity);
    }
}
