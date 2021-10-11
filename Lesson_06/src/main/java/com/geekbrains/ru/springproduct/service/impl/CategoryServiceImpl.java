package com.geekbrains.ru.springproduct.service.impl;


import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import com.geekbrains.ru.springproduct.repository.CategoryRepository;
import com.geekbrains.ru.springproduct.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

//    @Override
//    public Category findByAlias(String alias) {
//        return categoryRepository.findByAlias(alias).orElseThrow(EntityNotFoundException::new);
//    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        return null;
    }

    @Override
    public Page<CategoryEntity> findAllByPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }




}
