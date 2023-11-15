package com.ecommerce.admin.controllers.user;

import com.ecommerce.library.dtos.UserDTO;
import com.ecommerce.library.entities.CityEntity;
import com.ecommerce.library.entities.CountryEntity;
import com.ecommerce.library.entities.ShoppingCartEntity;
import com.ecommerce.library.entities.UserEntity;
import com.ecommerce.library.services.ICityService;
import com.ecommerce.library.services.ICountryService;
import com.ecommerce.library.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICountryService countryService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String profile(Model model, Principal principal){
        if (principal == null){
            return "login";
        } else {
            List<CountryEntity> countryList = countryService.findAll();
            model.addAttribute("title", "Profile");
            model.addAttribute("page", "Profile");
            //model.addAttribute("countries",countryList);
            String username = principal.getName();
            UserDTO user = userService.findByUsername(username);
            model.addAttribute("customer",user);
            return "customer-information";
        }
    }

    @GetMapping("/change-password")
    public String changePassword(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Change password");
        model.addAttribute("page", "Change password");
        return "change-password";
    }
    @PostMapping("/update-profile/{userId}")
    public String updateProfile(@ModelAttribute("customer") UserDTO user,
                                @PathVariable Long userId,
                                RedirectAttributes redirectAttributes,
                                Model model,
                                Principal principal){
        if (principal == null){
            return "redirect:/login";
        } else {
            String username = principal.getName();
            List<CountryEntity> countryList = countryService.findAll();
            List<CityEntity> cities = cityService.findAll();
            model.addAttribute("countries", countryList);
            model.addAttribute("cities", cities);
            UserDTO updatedUser = userService.update(userId,user);
            if (updatedUser == null){
                return "customer-information";
            }
            UserDTO userUpdate = userService.findByUsername(principal.getName());
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
            model.addAttribute("customer", userUpdate);
            return "redirect:/profile";
        }
    }
    @PostMapping("/change-password")
    public String changePass(@RequestParam("oldPassword") String oldPassword,
                             @RequestParam("newPassword") String newPassword,
                             @RequestParam("repeatNewPassword") String repeatPassword,
                             RedirectAttributes attributes,
                             Model model,
                             Principal principal) {
        if (principal == null){
            return "redirect:/login";
        } else {
            UserDTO user = userService.findByUsername(principal.getName());
            if (passwordEncoder.matches(oldPassword, user.getPassword())
                    && !passwordEncoder.matches(oldPassword,newPassword)
                    && !passwordEncoder.matches(oldPassword, user.getPassword())
                    && repeatPassword.equals(newPassword) && newPassword.length() >=8
            ){
                user.setPassword(passwordEncoder.encode(newPassword));
                userService.changePassword(user);
                attributes.addFlashAttribute("success", "Your password has been changed successfully!");
                return "redirect:/profile";
            }
            else {
                model.addAttribute("message", "Your password is wrong");
                return "change-password";
            }
        }
    }
}
