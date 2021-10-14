package com.geekbrains.ru.springproduct.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    void testFindAll() {
        ArrayList<CategoryEntity> categoryEntityList = new ArrayList<CategoryEntity>();
        when(this.categoryRepository.findAll()).thenReturn(categoryEntityList);
        List<CategoryEntity> actualFindAllResult = this.categoryServiceImpl.findAll();
        assertSame(categoryEntityList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.categoryRepository).findAll();
    }

    @Test
    void testFindById() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");
        categoryEntity.setProduct(new HashSet<ProductEntity>());
        Optional<CategoryEntity> ofResult = Optional.<CategoryEntity>of(categoryEntity);
        when(this.categoryRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(categoryEntity, this.categoryServiceImpl.findById(123L));
        verify(this.categoryRepository).findById((Long) any());
        assertTrue(this.categoryServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindById2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");
        categoryEntity.setProduct(new HashSet<ProductEntity>());
        Optional<CategoryEntity> ofResult = Optional.<CategoryEntity>of(categoryEntity);
        when(this.categoryRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(categoryEntity, this.categoryServiceImpl.findById(0L));
        verify(this.categoryRepository).findById((Long) any());
        assertTrue(this.categoryServiceImpl.findAll().isEmpty());
    }

    @Test
    void testSave() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");
        categoryEntity.setProduct(new HashSet<ProductEntity>());
        assertNull(this.categoryServiceImpl.save(categoryEntity));
    }

    @Test
    void testFindAllByPage() {
        PageImpl<CategoryEntity> pageImpl = new PageImpl<CategoryEntity>(new ArrayList<CategoryEntity>());
        when(this.categoryRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        Page<CategoryEntity> actualFindAllByPageResult = this.categoryServiceImpl.findAllByPage(null);
        assertSame(pageImpl, actualFindAllByPageResult);
        assertTrue(actualFindAllByPageResult.toList().isEmpty());
        verify(this.categoryRepository).findAll((org.springframework.data.domain.Pageable) any());
        assertTrue(this.categoryServiceImpl.findAll().isEmpty());
    }
}

