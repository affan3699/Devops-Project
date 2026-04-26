package com.example.chatbot.dto;

public class LLMResponseDTO {
    public Long id;
    public String question;
    public String answer;

    public LLMResponseDTO(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "LLMResponseDTO{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
