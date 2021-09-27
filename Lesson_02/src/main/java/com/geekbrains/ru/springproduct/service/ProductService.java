package com.geekbrains.ru.springproduct.service;

import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.dto.ProductViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductService {

    List<ProductViewDto> findAll();

    ProductEntity findById(long id);

    ProductViewDto save(ProductViewDto productViewDto);

    Page<ProductEntity> findAllByPage(Pageable pageable);

    List<ProductEntity> findMaxPrice(double price);

    List<ProductEntity> findMinPrice(double price);

    ProductEntity saveWithImage(ProductEntity product, MultipartFile image);

    void deleteById(Long productId);
}
