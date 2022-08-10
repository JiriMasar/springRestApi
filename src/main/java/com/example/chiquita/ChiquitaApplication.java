package com.example.chiquita;

import com.example.chiquita.entities.RoleEntity;
import com.example.chiquita.repositories.RoleRepository;
import com.example.chiquita.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class ChiquitaApplication {

    private final RoleRepository roleRepository;

    @Autowired
    public ChiquitaApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ChiquitaApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner() throws Exception {
        return args -> {
            try {
                roleRepository.saveAll(List.of(
                        new RoleEntity("ADMIN"),
                        new RoleEntity("USER_BASIC"),
                        new RoleEntity("USER_ADVANCED")
                ));
            } catch (Exception e) {

            }
        };
    }
}
