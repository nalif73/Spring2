package com.geekbrains.ru.springproduct.service.impl;

import com.geekbrains.ru.springproduct.domain.RoleEntity;
import com.geekbrains.ru.springproduct.domain.UserEntity;
import com.geekbrains.ru.springproduct.repository.UserRepository;
import com.geekbrains.ru.springproduct.service.RoleService;
import com.geekbrains.ru.springproduct.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
    }

    @Override
    public UserEntity save(UserEntity user) {
        RoleEntity userRole = roleService.findByName("ROLE_USER");

        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public void setEnable(Long userId, Boolean enable) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        user.setEnabled(enable);

        userRepository.save(user);
    }

    @Override
    public Page<UserEntity> findAllByPage(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

}