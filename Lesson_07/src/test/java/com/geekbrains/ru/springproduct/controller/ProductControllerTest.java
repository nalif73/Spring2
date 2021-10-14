package com.geekbrains.ru.springproduct.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.service.CategoryService;
import com.geekbrains.ru.springproduct.service.ProductService;

import java.util.ArrayList;
import java.util.HashSet;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @MockBean
    private Validator validator;

    @Test
    void testAddProduct() throws Exception {
        when(this.categoryService.findAll()).thenReturn(new ArrayList<CategoryEntity>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/form");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories", "product", "violations"))
                .andExpect(MockMvcResultMatchers.view().name("product/form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/form"));
    }

    @Test
    void testAddProduct2() throws Exception {
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
        when(this.productService.findById(anyLong())).thenReturn(productEntity);
        when(this.categoryService.findAll()).thenReturn(new ArrayList<CategoryEntity>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/form");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories", "product", "violations"))
                .andExpect(MockMvcResultMatchers.view().name("product/form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/form"));
    }

    @Test
    void testDeleteProductById() throws Exception {
        doNothing().when(this.productService).deleteById((Long) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/delete");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        when(this.productService.findAll()).thenReturn(new ArrayList<ProductEntity>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("product/products"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/products"));
    }

    @Test
    void testGetAllProducts2() throws Exception {
        when(this.productService.findAll()).thenReturn(new ArrayList<ProductEntity>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("product/products"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/products"));
    }

    @Test
    void testSaveProduct() throws Exception {
        when(this.productService.findAll()).thenReturn(new ArrayList<ProductEntity>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("product/products"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/products"));
    }

    @Test
    void testSaveProduct2() throws Exception {
        when(this.productService.findAll()).thenReturn(new ArrayList<ProductEntity>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("product/products"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/products"));
    }

    @Test
    void testShowMaxPriceProduct() throws Exception {
        when(this.productService.findMaxPrice(anyDouble())).thenReturn(new ArrayList<ProductEntity>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/maxprice");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("price", String.valueOf(10.0));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("product/products"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/products"));
    }

    @Test
    void testShowMinPriceProduct() throws Exception {
        when(this.productService.findMinPrice(anyDouble())).thenReturn(new ArrayList<ProductEntity>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/minprice");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("price", String.valueOf(10.0));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("product/products"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/products"));
    }
}

