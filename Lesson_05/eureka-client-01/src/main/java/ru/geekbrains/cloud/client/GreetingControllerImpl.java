package ru.geekbrains.cloud.client;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GreetingControllerImpl implements GreetingController {
    private final EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

   @Value("${userValue01}")
   private String username;

    @Override
    @GetMapping("/greeting")
    public String greeting() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }

    @GetMapping("/user")
    public String getUsername() {
        return String.format("Hello '%s'!", username);
    }

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException();
    }

}
