package com.geekbrains.ru.springproduct.service;


import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> findAll();

    CategoryEntity findById(Long id);

 //   Category findByAlias(String alias);

    CategoryEntity save(CategoryEntity category);

    Page<CategoryEntity> findAllByPage(Pageable pageable);

}
