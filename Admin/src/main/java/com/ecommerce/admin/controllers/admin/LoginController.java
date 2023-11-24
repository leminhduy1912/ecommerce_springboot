package com.ecommerce.admin.controllers.admin;

import com.ecommerce.library.dtos.UserDTO;
import com.ecommerce.library.entities.RoleEntity;
import com.ecommerce.library.services.IRoleService;
import com.ecommerce.library.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class LoginController {
    @Autowired
    private IUserService adminService;
    @Autowired
    private IRoleService roleService;



    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(Model model){

        return "/admin/sign-in";
    }
    @GetMapping("/admin/dashboard")
    public String dashBoard(Model model){
        //model.addAttribute("title", "Login");
        return "/admin/dashboard";
    }

    @GetMapping("/admin/index")
    public String home(Model model){
        model.addAttribute("title", "Home Page");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof  AnonymousAuthenticationToken){
            return "login";
        }
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("adminDto", new UserDTO());
        return "admin/sign-up";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto") UserDTO adminDto,
                              BindingResult result,
                              Model model){

        try {

            if(result.hasErrors()){
                model.addAttribute("adminDto", adminDto);

                return "admin/sign-up";
            }

            UserDTO admin = adminService.findByUsername(adminDto.getUsername());

            if(admin.getId() != null){
                model.addAttribute("adminDto", adminDto);

                model.addAttribute("emailError", "Your email has been registered!");
                return "admin/sign-up";
            }
            else{
                adminDto.setRoleCodes(Arrays.asList("ADMIN"));
                UserDTO dto = adminService.save(adminDto);
                model.addAttribute("success", "Register successfully!");

                return "admin/sign-up";
            }
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errors", "The server has been wrong!");
        }
        return "admin/sign-up";

    }






}