package com.ecommerce.admin.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class AdminConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new AdminServiceConfig();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/customer/**").hasAuthority("CUSTOMER")
                .antMatchers("/*").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/do-login")
                .successHandler((request, response, authentication) -> {
                    // Lấy danh sách quyền của người dùng
                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                    // Nếu người dùng có quyền ADMIN
                    if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
                        response.sendRedirect("/admin/dashboard");
                    }
                    // Nếu người dùng có quyền CUSTOMER
                    else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("CUSTOMER"))) {
                        response.sendRedirect("/customer/index");
                    }
                })
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        http
                .sessionManagement()
                .sessionFixation().migrateSession()
                .maximumSessions(1).maxSessionsPreventsLogin(false)
                .expiredUrl("/login?expired");
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();

    }

}
