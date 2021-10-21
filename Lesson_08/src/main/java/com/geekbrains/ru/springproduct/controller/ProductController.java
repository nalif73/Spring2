package com.geekbrains.ru.springproduct.controller;


import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.service.CategoryService;
import com.geekbrains.ru.springproduct.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.geekbrains.ru.springproduct.dto.ProductViewDto;
import static com.geekbrains.ru.springproduct.domain.constant.RestControllerName.API_V1;
import static com.geekbrains.ru.springproduct.domain.constant.RestControllerName.PRODUCT;

@RestController
@RequestMapping(API_V1 + PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductViewDto> getProducts() {
        return productService.findAll();
    }

    @PostMapping
    public ProductViewDto saveProduct(@RequestBody ProductViewDto productViewDto) {
        return productService.save(productViewDto);
    }


}
