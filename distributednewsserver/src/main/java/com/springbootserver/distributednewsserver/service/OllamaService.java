package com.springbootserver.distributednewsserver.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OllamaService {
    private static final String OLLAMA_API_URL = "http://localhost:11434/api/chat";

    @Autowired
    private RestTemplate ollamaRestTemplate;

   public String chatWithOllama(String model, String userMessage) {
        // Instruction to the assistant on how to respond
        String systemInstruction = """
            You are a helpful assistant. Always respond in a concise, bullet-point format.
            Use professional tone. If numerical data is included, round to 2 decimal places.
            Structure information clearly using headings where appropriate.
        """;

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", model);

        Map<String, String> systemMsg = Map.of(
            "role", "system",
            "content", systemInstruction
        );

        Map<String, String> userMsg = Map.of(
            "role", "user",
            "content", userMessage
        );

        payload.put("messages", new Map[]{systemMsg, userMsg});

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = ollamaRestTemplate.postForEntity(OLLAMA_API_URL, request, String.class);

        return response.getBody();
    }

    
}
