package com.geekbrains.ru.springproduct.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import com.geekbrains.ru.springproduct.domain.ProductEntity;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class ShopCartTest {
    @Test
    void testAddProduct() {
        ShopCart shopCart = new ShopCart();

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
        shopCart.addProduct(productEntity);
        assertEquals(1, shopCart.getCount());
    }

    @Test
    void testAddProduct2() {
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

        ShopCart shopCart = new ShopCart();
        shopCart.addProduct(productEntity);

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
        shopCart.addProduct(productEntity1);
        assertEquals(2, shopCart.getCount());
    }

    @Test
    void testRemoveProduct() {
        ShopCart shopCart = new ShopCart();

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
        assertThrows(IllegalArgumentException.class, () -> shopCart.removeProduct(productEntity));
    }

    @Test
    void testRemoveProduct2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Продукт не найден в корзине");
        categoryEntity.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCategory(categoryEntity);
        productEntity.setImageLink("Продукт не найден в корзине");
        productEntity.setPrice(10.0);
        productEntity.setId(123L);
        productEntity.setName("Продукт не найден в корзине");

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

        ShopCart shopCart = new ShopCart();
        shopCart.addProduct(productEntity1);
        shopCart.addProduct(productEntity);

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
        shopCart.removeProduct(productEntity2);
        assertEquals(1, shopCart.getCount());
    }

    @Test
    void testRemoveProduct3() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Продукт не найден в корзине");
        categoryEntity.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCategory(categoryEntity);
        productEntity.setImageLink("Продукт не найден в корзине");
        productEntity.setPrice(10.0);
        productEntity.setId(123L);
        productEntity.setName("Продукт не найден в корзине");

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
        shopCart.addProduct(productEntity);

        CategoryEntity categoryEntity3 = new CategoryEntity();
        categoryEntity3.setId(123L);
        categoryEntity3.setName("Name");
        categoryEntity3.setProduct(new HashSet<ProductEntity>());

        ProductEntity productEntity3 = new ProductEntity();
        productEntity3.setProductCategory(categoryEntity3);
        productEntity3.setImageLink("Image Link");
        productEntity3.setPrice(10.0);
        productEntity3.setId(123L);
        productEntity3.setName("Name");
        shopCart.removeProduct(productEntity3);
        assertEquals(2, shopCart.getCount());
    }

    @Test
    void testGetProductsWithCount() {
        assertTrue((new ShopCart()).getProductsWithCount().isEmpty());
    }

    @Test
    void testGetCount() {
        assertEquals(0, (new ShopCart()).getCount());
    }
}

