package com.ecommerce.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages ={"com.ecommerce.library.*","com.ecommerce.admin.*"})
@EnableJpaRepositories(value = "com.ecommerce.library.repositories")
@EntityScan(value = "com.ecommerce.library.entities")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}