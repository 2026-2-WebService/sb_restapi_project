package com.example.__sb_restapi_project.service;

import com.example.__sb_restapi_project.dto.GreetingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService {

    private static final Map<String, String> TEMPLATES = Map.of(
            "en", "Hello, %s!",
            "ko", "안녕하세요, %s님!",
            "es", "¡Hola, %s!",
            "fr", "Bonjour, %s !",
            "ja", "こんにちは、%sさん！"
    );

    private final AtomicLong counter = new AtomicLong();

    public GreetingResponse createGreeting(
            String name,
            String lang
    ) {

        String template = TEMPLATES.get(lang);

        // 지원하지 않는 언어 처리
        if (template == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "지원하지 않는 언어입니다: " + lang
            );
        }

        String content = String.format(template, name);

        long id = counter.incrementAndGet();

        return new GreetingResponse(
                id,
                content,
                lang
        );
    }

    public List<String> getSupportedLanguages() {
        return List.of(
                "en",
                "es",
                "fr",
                "ja",
                "ko"
        );
    }
}