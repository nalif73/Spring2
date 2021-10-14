package com.geekbrains.ru.springproduct.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.repository.ProductRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Test
    void testFindAll() {
        ArrayList<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
        when(this.productRepository.findAll()).thenReturn(productEntityList);
        List<ProductEntity> actualFindAllResult = this.productServiceImpl.findAll();
        assertSame(productEntityList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.productRepository).findAll();
    }

    @Test
    void testFindById() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");
        categoryEntity.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCategory(categoryEntity);
        productEntity.setImageLink("Image Link");
        productEntity.setPrice(10.0);
        productEntity.setId(123L);
        productEntity.setName("Name");
        Optional<ProductEntity> ofResult = Optional.<ProductEntity>of(productEntity);
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(productEntity, this.productServiceImpl.findById(123L));
        verify(this.productRepository).findById((Long) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindById2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");
        categoryEntity.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCategory(categoryEntity);
        productEntity.setImageLink("Image Link");
        productEntity.setPrice(10.0);
        productEntity.setId(123L);
        productEntity.setName("Name");
        Optional<ProductEntity> ofResult = Optional.<ProductEntity>of(productEntity);
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(productEntity, this.productServiceImpl.findById(0L));
        verify(this.productRepository).findById((Long) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testSave() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");
        categoryEntity.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCategory(categoryEntity);
        productEntity.setImageLink("Image Link");
        productEntity.setPrice(10.0);
        productEntity.setId(123L);
        productEntity.setName("Name");
        when(this.productRepository.save((ProductEntity) any())).thenReturn(productEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Name");
        categoryEntity1.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setProductCategory(categoryEntity1);
        productEntity1.setImageLink("Image Link");
        productEntity1.setPrice(10.0);
        productEntity1.setId(123L);
        productEntity1.setName("Name");
        assertSame(productEntity, this.productServiceImpl.save(productEntity1));
        verify(this.productRepository).save((ProductEntity) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindAllByPage() {
        PageImpl<ProductEntity> pageImpl = new PageImpl<ProductEntity>(new ArrayList<ProductEntity>());
        when(this.productRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        Page<ProductEntity> actualFindAllByPageResult = this.productServiceImpl.findAllByPage(null);
        assertSame(pageImpl, actualFindAllByPageResult);
        assertTrue(actualFindAllByPageResult.toList().isEmpty());
        verify(this.productRepository).findAll((org.springframework.data.domain.Pageable) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindMinPrice() {
        ArrayList<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
        when(this.productRepository.findAllByPriceLessThan(anyDouble())).thenReturn(productEntityList);
        List<ProductEntity> actualFindMinPriceResult = this.productServiceImpl.findMinPrice(10.0);
        assertSame(productEntityList, actualFindMinPriceResult);
        assertTrue(actualFindMinPriceResult.isEmpty());
        verify(this.productRepository).findAllByPriceLessThan(anyDouble());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindMaxPrice() {
        ArrayList<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
        when(this.productRepository.findAllByPriceGreaterThan(anyDouble())).thenReturn(productEntityList);
        List<ProductEntity> actualFindMaxPriceResult = this.productServiceImpl.findMaxPrice(10.0);
        assertSame(productEntityList, actualFindMaxPriceResult);
        assertTrue(actualFindMaxPriceResult.isEmpty());
        verify(this.productRepository).findAllByPriceGreaterThan(anyDouble());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testSaveWithImage() throws IOException {
        when(this.productRepository.save((ProductEntity) any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");
        categoryEntity.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCategory(categoryEntity);
        productEntity.setImageLink("Image Link");
        productEntity.setPrice(10.0);
        productEntity.setId(123L);
        productEntity.setName("Name");
        assertThrows(EntityNotFoundException.class, () -> this.productServiceImpl.saveWithImage(productEntity,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")))));
        verify(this.productRepository).save((ProductEntity) any());
    }

    @Test
    void testDeleteById() {
        doNothing().when(this.productRepository).deleteById((Long) any());
        this.productServiceImpl.deleteById(123L);
        verify(this.productRepository).deleteById((Long) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }
}

