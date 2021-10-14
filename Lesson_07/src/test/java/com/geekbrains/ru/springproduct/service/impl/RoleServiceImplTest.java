package com.geekbrains.ru.springproduct.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.geekbrains.ru.springproduct.domain.RoleEntity;
import com.geekbrains.ru.springproduct.repository.RoleRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoleServiceImplTest {
    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Test
    void testFindByName() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(123L);
        roleEntity.setName("Name");
        Optional<RoleEntity> ofResult = Optional.<RoleEntity>of(roleEntity);
        when(this.roleRepository.findByNameIgnoreCase((String) any())).thenReturn(ofResult);
        assertSame(roleEntity, this.roleServiceImpl.findByName("Name"));
        verify(this.roleRepository).findByNameIgnoreCase((String) any());
    }

    @Test
    void testFindByName2() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(123L);
        roleEntity.setName("Name");
        Optional<RoleEntity> ofResult = Optional.<RoleEntity>of(roleEntity);
        when(this.roleRepository.findByNameIgnoreCase((String) any())).thenReturn(ofResult);
        assertSame(roleEntity, this.roleServiceImpl.findByName("42"));
        verify(this.roleRepository).findByNameIgnoreCase((String) any());
    }
}

