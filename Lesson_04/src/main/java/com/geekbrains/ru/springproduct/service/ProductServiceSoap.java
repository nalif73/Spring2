package com.geekbrains.ru.springproduct.service;

import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.repository.ProductRepository;
import com.geekbrains.ru.springproduct.soap.products.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceSoap {
    private final ProductRepository productRepository;

    public static final Function<ProductEntity, Product> functionEntityToSoap = se -> {
        Product s = new Product();
        s.setId(se.getId());
        s.setName(se.getName());
        s.setCategoriesName(se.getProductCategory().getName());
        return s;
    };

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(functionEntityToSoap)
                .collect(Collectors.toList());
    }

    public Product getProductById(long id) {
        return productRepository.findById(id)
                .map(functionEntityToSoap)
                .get();
    }
}
