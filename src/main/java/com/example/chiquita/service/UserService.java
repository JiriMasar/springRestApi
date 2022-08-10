package com.example.chiquita.service;

import com.example.chiquita.entities.RoleEntity;
import com.example.chiquita.entities.UserEntity;
import com.example.chiquita.repositories.RoleRepository;
import com.example.chiquita.repositories.UserRepository;
import com.example.chiquita.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public UserEntity saveUser(RegisterRequest registerRequest) {
        UserEntity user = new UserEntity();

        user.setEmail(registerRequest.email());
        user.setActive(true);
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));

        RoleEntity userRole = roleRepository.findByRole("USER_BASIC");
        user.setRoles(new HashSet<RoleEntity>(Collections.singletonList(userRole)));

        return userRepository.save(user);
    }

    public UserEntity getById(UUID id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Cannot find [%s] user", id)));
    }
}
