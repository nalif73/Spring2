package com.geekbrains.ru.springproduct.service;

import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import com.geekbrains.ru.springproduct.repository.CategoryRepository;
import com.geekbrains.ru.springproduct.soap.categories.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryServiceSoap {
    private final CategoryRepository categoryRepository;

    public static final Function<CategoryEntity, Category> functionEntityToSoap = ge -> {
        Category g = new Category();
        g.setName(g.getName());
        ge.getProduct().stream().map(ProductServiceSoap.functionEntityToSoap).forEach(s -> g.getProducts().add(s));
        return g;
    };

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).map(functionEntityToSoap).get();
    }
}
