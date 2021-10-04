package com.geekbrains.ru.springproduct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.nio.file.Paths;

@Configuration
public class MainConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "data";
        String staticPath = Paths.get(System.getProperty("user.dir"), dirName).toFile().getAbsolutePath();

        registry.addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file:/"+  staticPath + "/");
    }
    @Bean
    public String querySubSelect() {
        return "select c.id from category c";
    }

}
