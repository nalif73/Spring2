package ru.geekbrains.cloud.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface GreetingController {
    @GetMapping("/greeting")
    String greeting();


}
