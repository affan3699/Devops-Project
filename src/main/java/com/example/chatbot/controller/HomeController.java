package com.example.chatbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/message")
    public String home() {
        return "index"; // This will literally return the string "index" to the browser
    }
}
