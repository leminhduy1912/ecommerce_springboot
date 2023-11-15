package com.ecommerce.admin.configs;

import com.ecommerce.library.dtos.UserDTO;
import com.ecommerce.library.entities.UserEntity;
import com.ecommerce.library.repositories.UserRepository;
import com.ecommerce.library.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceConfig implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO admin = userService.findByUsername(username);
        System.out.println("username" + username);
        System.out.println("user duoc tim thay la" + admin);

        if (admin == null) {
            throw new UsernameNotFoundException("Could not find username");
        }
        List<String> roleCodes = admin.getRoleCodes();
        return new User(
                admin.getUsername(),
                admin.getPassword(),
                roleCodes
                        .stream()
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
