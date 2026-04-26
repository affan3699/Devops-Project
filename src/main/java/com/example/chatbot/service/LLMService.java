package com.example.chatbot.service;

import com.example.chatbot.dto.LLMResponseDTO;
import com.example.chatbot.dto.ResponseModel;
import com.example.chatbot.entity.Question;
import com.example.chatbot.exception.ApiException;
import com.example.chatbot.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LLMService {

    private static final Logger logger = LoggerFactory.getLogger(LLMService.class);

    private final QuestionRepository questionRepository;
    private final HuggingFaceService huggingFaceService;

    public LLMService(QuestionRepository questionRepository, HuggingFaceService huggingFaceService) {
        this.questionRepository = questionRepository;
        this.huggingFaceService = huggingFaceService;
    }

    public ResponseEntity<ResponseModel<Object>> askQuestion(Long id) {
        logger.info("askQuestion request received");
        // Validate input
        if (id == null || id <= 0) {
            throw new ApiException("01", "INVALID_REQUEST", "Question id is invalid");
        }

        // Fetching question from database using ID
        logger.info("Fetching Question From DB of Id = {}", id);
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ApiException("14", "NOT_FOUND", "Question not found with id: " + id));
        logger.info("Question Found in database = {}", question.getQuestionText());

        // Calling LLM
        logger.info("Asking question from LLM.....");
        String answer = huggingFaceService.getAnswer(question.getQuestionText());
        logger.info("Answer Received = {}", answer);

        ResponseModel<Object> responseBody =
                ResponseModel.approved(
                        "APPROVED",
                        new LLMResponseDTO(question.getId(), question.getQuestionText(), answer)
                );

        logger.info("Response returned = {}", responseBody.toString());

        return ResponseEntity.ok(responseBody);
    }

    public ResponseEntity<ResponseModel<Object>> askQuestion2(String question) {
        logger.info("askQuestion2 request received");
        // Validate input
        if (question == null || question.isEmpty()) {
            throw new ApiException("01", "INVALID_REQUEST", "Question is invalid");
        }

        // Calling LLM
        logger.info("Asking question from LLM.....");
        String answer = huggingFaceService.getAnswer(question);
        logger.info("Answer Received = {}", answer);

        ResponseModel<Object> responseBody =
                ResponseModel.approved(
                        "APPROVED",
                        new LLMResponseDTO(null, question, answer)
                );

        logger.info("Response returned = {}", responseBody);

        return ResponseEntity.ok(responseBody);
    }
}
