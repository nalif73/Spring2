package com.geekbrains.ru.springproduct.repository;


import com.geekbrains.ru.springproduct.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    //    List<Product> findAllByPriceLessThan(double price);
    List<ProductEntity> findAllByPriceLessThan(double price);

    List<ProductEntity> findAllByPriceGreaterThan(double price);


}
