package com.example.chatbot.service;

import com.example.chatbot.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HuggingFaceService {

    private static final Logger logger = LoggerFactory.getLogger(HuggingFaceService.class);

    @Value("${huggingface.api.key}")
    private String hfToken;

    private final String HF_API_URL = "https://router.huggingface.co/v1/chat/completions";

    public String getAnswer(String question) {
        RestTemplate restTemplate = new RestTemplate();

        // Prepare request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(hfToken);

        // Prepare JSON body
        Map<String, Object> body = new HashMap<>();
        body.put("model", "openai/gpt-oss-20b:cheapest");
        body.put("stream", false);
        body.put("messages", List.of(
                Map.of("role", "user", "content", question)
        ));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        // Send POST request
        ResponseEntity<Map> response = restTemplate.exchange(
                HF_API_URL,
                HttpMethod.POST,
                entity,
                Map.class
        );

        // Extract assistant message
        try {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> firstChoice = choices.get(0);
            Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
            return message.get("content").toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApiException("91", "LLM_FAILED", "LLM failed: " + e.getMessage());
        }
    }
}