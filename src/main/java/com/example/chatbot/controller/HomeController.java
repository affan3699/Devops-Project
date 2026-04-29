package com.example.chatbot.controller;

import com.example.chatbot.service.LLMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/api/message")
    public String home() {
        logger.info("Service running {}", LocalDateTime.now());
        return "Service running " + LocalDateTime.now();
    }
}
