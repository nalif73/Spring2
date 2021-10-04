package com.geekbrains.ru.springproduct.repository;


import com.geekbrains.ru.springproduct.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
   @Query("select g from CategoryEntity g where g.name = ?1")
   Optional<CategoryEntity> findByName(String name);
}
