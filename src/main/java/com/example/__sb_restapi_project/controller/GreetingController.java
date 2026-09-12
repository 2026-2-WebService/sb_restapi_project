package com.example.__sb_restapi_project.controller;

import com.example.__sb_restapi_project.dto.GreetingResponse;
import com.example.__sb_restapi_project.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public GreetingResponse greet(
            @RequestParam(defaultValue = "World") String name,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return greetingService.createGreeting(
                name,
                lang
        );
    }

    @GetMapping("/languages")
    public List<String> languages() {
        return greetingService.getSupportedLanguages();
    }
}