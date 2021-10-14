package com.geekbrains.ru.springproduct.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.geekbrains.ru.springproduct.component.ShopCart;
import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.repository.ProductRepository;
import com.geekbrains.ru.springproduct.service.ProductService;
import com.geekbrains.ru.springproduct.service.impl.ProductServiceImpl;

import java.util.HashSet;
import java.util.Optional;

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
import org.springframework.web.servlet.view.RedirectView;

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
class CartControllerTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private ProductService productService;

    @Test
    void testAddToCart() {
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
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById((Long) any())).thenReturn(Optional.<ProductEntity>of(productEntity));
        CartController cartController = new CartController(new ProductServiceImpl(productRepository));
        ShopCart shopCart = new ShopCart();
        RedirectView actualAddToCartResult = cartController.addToCart(123L, shopCart);
        assertFalse(actualAddToCartResult.isPropagateQueryProperties());
        assertFalse(actualAddToCartResult.isExposePathVariables());
        assertEquals("/product", actualAddToCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualAddToCartResult.getContentType());
        assertTrue(actualAddToCartResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Long) any());
        assertEquals(1, shopCart.getCount());
    }

    @Test
    void testAddToCart2() {
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
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById((Long) any())).thenReturn(Optional.<ProductEntity>of(productEntity));
        CartController cartController = new CartController(new ProductServiceImpl(productRepository));

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("/product");
        categoryEntity1.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setProductCategory(categoryEntity1);
        productEntity1.setImageLink("/product");
        productEntity1.setPrice(10.0);
        productEntity1.setId(123L);
        productEntity1.setName("/product");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(123L);
        categoryEntity2.setName("Name");
        categoryEntity2.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setProductCategory(categoryEntity2);
        productEntity2.setImageLink("Image Link");
        productEntity2.setPrice(10.0);
        productEntity2.setId(123L);
        productEntity2.setName("Name");

        ShopCart shopCart = new ShopCart();
        shopCart.addProduct(productEntity2);
        shopCart.addProduct(productEntity1);
        RedirectView actualAddToCartResult = cartController.addToCart(123L, shopCart);
        assertFalse(actualAddToCartResult.isPropagateQueryProperties());
        assertFalse(actualAddToCartResult.isExposePathVariables());
        assertEquals("/product", actualAddToCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualAddToCartResult.getContentType());
        assertTrue(actualAddToCartResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Long) any());
        assertEquals(3, shopCart.getCount());
    }

    @Test
    void testRemoveFromCart() {
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
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById((Long) any())).thenReturn(Optional.<ProductEntity>of(productEntity));
        CartController cartController = new CartController(new ProductServiceImpl(productRepository));

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Продукт не найден в корзине");
        categoryEntity1.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setProductCategory(categoryEntity1);
        productEntity1.setImageLink("Продукт не найден в корзине");
        productEntity1.setPrice(10.0);
        productEntity1.setId(123L);
        productEntity1.setName("Продукт не найден в корзине");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(123L);
        categoryEntity2.setName("Name");
        categoryEntity2.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setProductCategory(categoryEntity2);
        productEntity2.setImageLink("Image Link");
        productEntity2.setPrice(10.0);
        productEntity2.setId(123L);
        productEntity2.setName("Name");

        ShopCart shopCart = new ShopCart();
        shopCart.addProduct(productEntity2);
        shopCart.addProduct(productEntity1);
        RedirectView actualRemoveFromCartResult = cartController.removeFromCart(123L, shopCart);
        assertFalse(actualRemoveFromCartResult.isPropagateQueryProperties());
        assertFalse(actualRemoveFromCartResult.isExposePathVariables());
        assertEquals("/cart", actualRemoveFromCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualRemoveFromCartResult.getContentType());
        assertTrue(actualRemoveFromCartResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Long) any());
        assertEquals(1, shopCart.getCount());
    }

    @Test
    void testRemoveFromCart2() {
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
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById((Long) any())).thenReturn(Optional.<ProductEntity>of(productEntity));
        CartController cartController = new CartController(new ProductServiceImpl(productRepository));

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Продукт не найден в корзине");
        categoryEntity1.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setProductCategory(categoryEntity1);
        productEntity1.setImageLink("Продукт не найден в корзине");
        productEntity1.setPrice(10.0);
        productEntity1.setId(123L);
        productEntity1.setName("Продукт не найден в корзине");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(123L);
        categoryEntity2.setName("Name");
        categoryEntity2.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setProductCategory(categoryEntity2);
        productEntity2.setImageLink("Image Link");
        productEntity2.setPrice(10.0);
        productEntity2.setId(123L);
        productEntity2.setName("Name");

        CategoryEntity categoryEntity3 = new CategoryEntity();
        categoryEntity3.setId(123L);
        categoryEntity3.setName("Name");
        categoryEntity3.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity3 = new ProductEntity();
        productEntity3.setProductCategory(categoryEntity3);
        productEntity3.setImageLink("Name");
        productEntity3.setPrice(10.0);
        productEntity3.setId(123L);
        productEntity3.setName("Name");

        CategoryEntity categoryEntity4 = new CategoryEntity();
        categoryEntity4.setId(123L);
        categoryEntity4.setName("Name");
        categoryEntity4.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity4 = new ProductEntity();
        productEntity4.setProductCategory(categoryEntity4);
        productEntity4.setImageLink("Image Link");
        productEntity4.setPrice(10.0);
        productEntity4.setId(123L);
        productEntity4.setName("Name");

        ShopCart shopCart = new ShopCart();
        shopCart.addProduct(productEntity4);
        shopCart.addProduct(productEntity3);
        shopCart.addProduct(productEntity2);
        shopCart.addProduct(productEntity1);
        RedirectView actualRemoveFromCartResult = cartController.removeFromCart(123L, shopCart);
        assertFalse(actualRemoveFromCartResult.isPropagateQueryProperties());
        assertFalse(actualRemoveFromCartResult.isExposePathVariables());
        assertEquals("/cart", actualRemoveFromCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualRemoveFromCartResult.getContentType());
        assertTrue(actualRemoveFromCartResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Long) any());
        assertEquals(3, shopCart.getCount());
    }

    @Test
    void testShowCart() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart");
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("cart/cart"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("cart/cart"));
    }

    @Test
    void testShowCart2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/cart");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("cart/cart"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("cart/cart"));
    }
}

