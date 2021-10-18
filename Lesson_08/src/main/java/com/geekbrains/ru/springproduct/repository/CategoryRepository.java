package com.geekbrains.ru.springproduct.repository;


import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

//    Optional<Category> findByAlias(String alias);
}
