package com.example.chatbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HomeController {

    @GetMapping("/api/message")
    public String home() {
        return "Service running" + LocalDateTime.now();
    }
}
