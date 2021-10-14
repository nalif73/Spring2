package com.geekbrains.ru.springproduct.controller;


import com.geekbrains.ru.springproduct.component.ShopCart;
import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/cart")
@SessionAttributes("shopCart")
@AllArgsConstructor
public class CartController {

    private final ProductService productService;

    @GetMapping("")
    public String showCart() {
        return "cart/cart";
    }

    @GetMapping("/add-to-cart")
    public RedirectView addToCart(@RequestParam Long id, @ModelAttribute ShopCart cart) {
        ProductEntity product = productService.findById(id);
        cart.addProduct(product);

        return new RedirectView("/product");
    }

    @GetMapping("/remove-from-cart")
    public RedirectView removeFromCart(@RequestParam Long id, @ModelAttribute ShopCart cart) {
        ProductEntity product = productService.findById(id);
        cart.removeProduct(product);

        return new RedirectView("/cart");
    }

}
