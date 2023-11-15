package com.ecommerce.admin.controllers.user;

import com.ecommerce.library.dtos.ShoppingCartDTO;
import com.ecommerce.library.dtos.UserDTO;
import com.ecommerce.library.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private IUserService userService;
     @GetMapping("/customer/index")
    public String home(Model model, Principal principal, HttpSession session){
         model.addAttribute("title", "Home");
         model.addAttribute("page", "Home");
         if (principal != null){
             UserDTO user = userService.findByUsername(principal.getName());
             session.setAttribute("username",user.getFirstName()+" "+user.getLastName());
             ShoppingCartDTO shoppingCart = user.getShoppingCart();
             if (shoppingCart != null){
                 model.addAttribute("totalItems",shoppingCart.getTotalItems());
             }
         }
         return "user/index";
     }
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact");
        model.addAttribute("page", "Contact");
        return "contact-us";
    }
}
