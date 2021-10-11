package com.geekbrains.ru.springproduct.controller.advice;

import com.geekbrains.ru.springproduct.component.ShopCart;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalShoppingCart {

    @ModelAttribute("shopCart")
    public ShopCart shopCart() {
        return new ShopCart();
    }

}
