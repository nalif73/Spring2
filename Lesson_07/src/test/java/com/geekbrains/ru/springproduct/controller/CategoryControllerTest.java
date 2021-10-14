package com.geekbrains.ru.springproduct.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.service.CategoryService;

import java.util.ArrayList;
import java.util.HashSet;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private Validator validator;

    @Test
    void testGetCategoryForm() throws Exception {
        when(this.categoryService.findAll()).thenReturn(new ArrayList<CategoryEntity>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/form");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories", "category", "violations"))
                .andExpect(MockMvcResultMatchers.view().name("category/form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("category/form"));
    }

    @Test
    void testGetCategoryForm2() throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");
        categoryEntity.setProduct(new HashSet<ProductEntity>());
        when(this.categoryService.findById((Long) any())).thenReturn(categoryEntity);
        when(this.categoryService.findAll()).thenReturn(new ArrayList<CategoryEntity>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/category/form");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories", "category", "violations"))
                .andExpect(MockMvcResultMatchers.view().name("category/form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("category/form"));
    }

    @Test
    void testSaveCategory() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}

