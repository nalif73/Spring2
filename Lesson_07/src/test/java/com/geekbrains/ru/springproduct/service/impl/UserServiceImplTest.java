package com.geekbrains.ru.springproduct.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.geekbrains.ru.springproduct.domain.RoleEntity;
import com.geekbrains.ru.springproduct.domain.UserEntity;
import com.geekbrains.ru.springproduct.repository.UserRepository;
import com.geekbrains.ru.springproduct.service.RoleService;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleService roleService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testFindByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("iloveyou");
        userEntity.setUsername("janedoe");
        userEntity.setId(123L);
        userEntity.setEnabled(true);
        userEntity.setRoles(new ArrayList<RoleEntity>());
        Optional<UserEntity> ofResult = Optional.<UserEntity>of(userEntity);
        when(this.userRepository.findByUsername((String) any())).thenReturn(ofResult);
        assertSame(userEntity, this.userServiceImpl.findByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testFindByUsername2() {
        when(this.userRepository.findByUsername((String) any())).thenReturn(Optional.<UserEntity>empty());
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.findByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testSave() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("iloveyou");
        userEntity.setUsername("janedoe");
        userEntity.setId(123L);
        userEntity.setEnabled(true);
        userEntity.setRoles(new ArrayList<RoleEntity>());
        when(this.userRepository.save((UserEntity) any())).thenReturn(userEntity);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(123L);
        roleEntity.setName("Name");
        when(this.roleService.findByName((String) any())).thenReturn(roleEntity);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setPassword("iloveyou");
        userEntity1.setUsername("janedoe");
        userEntity1.setId(123L);
        userEntity1.setEnabled(true);
        userEntity1.setRoles(new ArrayList<RoleEntity>());
        assertSame(userEntity, this.userServiceImpl.save(userEntity1));
        verify(this.userRepository).save((UserEntity) any());
        verify(this.roleService).findByName((String) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
        assertEquals(1, userEntity1.getRoles().size());
        assertEquals("secret", userEntity1.getPassword());
    }

    @Test
    void testSetEnable() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("iloveyou");
        userEntity.setUsername("janedoe");
        userEntity.setId(123L);
        userEntity.setEnabled(true);
        userEntity.setRoles(new ArrayList<RoleEntity>());
        Optional<UserEntity> ofResult = Optional.<UserEntity>of(userEntity);

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setPassword("iloveyou");
        userEntity1.setUsername("janedoe");
        userEntity1.setId(123L);
        userEntity1.setEnabled(true);
        userEntity1.setRoles(new ArrayList<RoleEntity>());
        when(this.userRepository.save((UserEntity) any())).thenReturn(userEntity1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        this.userServiceImpl.setEnable(123L, true);
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((UserEntity) any());
    }

    @Test
    void testFindAllByPage() {
        PageImpl<UserEntity> pageImpl = new PageImpl<UserEntity>(new ArrayList<UserEntity>());
        when(this.userRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        Page<UserEntity> actualFindAllByPageResult = this.userServiceImpl.findAllByPage(null);
        assertSame(pageImpl, actualFindAllByPageResult);
        assertTrue(actualFindAllByPageResult.toList().isEmpty());
        verify(this.userRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
}

