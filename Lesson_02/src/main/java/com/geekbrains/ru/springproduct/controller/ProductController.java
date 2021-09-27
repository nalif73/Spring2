package com.geekbrains.ru.springproduct.controller;


import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.dto.ProductViewDto;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
//@SessionAttributes("shopCart")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final Validator validator;

    @GetMapping
    public List<ProductViewDto> getAllProducts() {

        return productService.findAll();
    }

    @GetMapping("/minprice")
    public String showMinPriceProduct(@RequestParam double price, Model model) {

        List<ProductEntity> products = productService.findMinPrice(price);
        model.addAttribute("products", products);

        return "product/products";
    }

    @GetMapping("/maxprice")
    public String showMaxPriceProduct(@RequestParam double price, Model model) {
        List<ProductEntity> products = productService.findMaxPrice(price);
        model.addAttribute("products", products);
        return "product/products";
    }

//    @GetMapping("/id")
//        public String getProduct(Model model, @PathVariable Long id) {
//        Product product = productService.findById(id).orElseThrow(IllegalArgumentException::new);
//
//        model.addAttribute("product", product);
//        return  "product/products";
//    }

    @PostMapping
    public ProductViewDto saveProduct(@RequestBody ProductViewDto productViewDto) {
        return productService.save(productViewDto);

        }
//
//        productService.saveWithImage(product, image);
//
//        return new RedirectView("product/");
//    }

    @GetMapping("/form")
    public String addProduct(@RequestParam(required = false) Long id, Model model,
                             @ModelAttribute(value = "violations") String violations) {

        if (id != null) {
            ProductEntity product = productService.findById(id);
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", new ProductEntity());
        }

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("violations", violations);

        return "product/form";
    }

    @GetMapping(value = "/delete")
    public RedirectView deleteProductById(@RequestParam Long id) {
        productService.deleteById(id);

        return new RedirectView("/product");
    }

//    @ExceptionHandler(Exception.class)
//    public String handleError(HttpServletRequest req, Exception ex) {
//        System.err.println("Request: " + req.getRequestURL() + " raised " + ex);
//
//        return "error";
//    }


}
