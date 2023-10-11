package com.ecommerce.admin.controllers;

import com.ecommerce.library.converters.AdminConverter;
import com.ecommerce.library.dtos.AdminDTO;

import com.ecommerce.library.services.IAdminService;
import com.ecommerce.library.services.impl.AdminServiceImpl;
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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class LoginController {
    @Autowired
    private IAdminService adminService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("title", "Login");
        return "login";
    }

    @RequestMapping("/index")
    public String home(Model model){
        model.addAttribute("title", "Home Page");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof  AnonymousAuthenticationToken){
            return "redirect:/login";
        }
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("title", "Register");
        model.addAttribute("adminDto", new AdminDTO());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto")AdminDTO adminDto,
                              BindingResult result,
                              Model model){

        try {

            if(result.hasErrors()){
                model.addAttribute("adminDto", adminDto);

                return "register";
            }

            AdminDTO admin = adminService.findByUsername(adminDto.getUsername());
            System.out.println("tim kiem admin " + admin);
            if(admin.getId() != null){
                model.addAttribute("adminDto", adminDto);
                System.out.println("admin not null");
                model.addAttribute("emailError", "Your email has been registered!");
                return "register";
            }
            else{
                adminDto.setRoleCodes(Arrays.asList("ADMIN"));
                AdminDTO dto = adminService.save(adminDto);
                model.addAttribute("success", "Register successfully!");
                System.out.println("thanh cong");
                return "register";
            }
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errors", "The server has been wrong!");
        }
        return "register";

    }






}