package com.example.chatbot.controller;

import com.example.chatbot.dto.LLMResponseDTO;
import com.example.chatbot.dto.ResponseModel;
import com.example.chatbot.service.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.chatbot.service.HuggingFaceService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/llm")
public class LlmController {

    @Autowired
    private HuggingFaceService huggingFaceService;

    @Autowired
    private LLMService llmService;

    @GetMapping("/ask")
    public String askQuestion(@RequestParam String question) {
        return huggingFaceService.getAnswer(question);
    }

    @GetMapping("/ask/{id}")
    public ResponseEntity<ResponseModel<Object>> askQuestion(@PathVariable Long id) {
        return llmService.askQuestion(id);
    }

    @GetMapping("/ask2")
    public ResponseEntity<ResponseModel<Object>> askQuestion2(@RequestParam String question) {
        return llmService.askQuestion2(question);
    }

    @GetMapping("/questions")
    public ResponseEntity<ResponseModel<Object>> findAllQuestions() {
        return llmService.findAllQuestions();
    }
}
