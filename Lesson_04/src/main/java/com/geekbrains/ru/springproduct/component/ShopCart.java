package com.geekbrains.ru.springproduct.component;

import com.geekbrains.ru.springproduct.domain.ProductEntity;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
public class ShopCart {


    private final Map<ProductEntity, Integer> productsWithCount = new HashMap<>();

    public void addProduct(ProductEntity product) {
        productsWithCount.merge(product, 1, (prev, cur) -> prev + 1);
    }

    public void removeProduct(ProductEntity product) {
        if (productsWithCount.containsKey(product)) {
            Integer count = productsWithCount.get(product);

            removeProduct(product, count);

            return;
        }

        throw new IllegalArgumentException("Продукт не найден в корзине");
    }

    private void removeProduct(ProductEntity product, Integer count) {
        if (count > 1) {
            productsWithCount.put(product, count - 1);
        } else {
            productsWithCount.remove(product);
        }
    }


    public Map<ProductEntity, Integer> getProductsWithCount() {
        return new HashMap<>(productsWithCount);
    }

    public int getCount() {
        return productsWithCount.values().stream()
                .reduce(0, Integer::sum);
    }

}
