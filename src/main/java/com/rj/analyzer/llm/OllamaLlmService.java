package com.rj.analyzer.llm;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.ai.model.chat", havingValue = "ollama", matchIfMissing = true)
public class OllamaLlmService implements LlmService {

    final OllamaChatModel chatModel;

    @Override
    public void analyzeError(String stackTrace, String inputLine) {
        String message = "Analyze the following Java stack trace and the corresponding input data. Identify the root cause, what data or input is missing, and suggest a solution. \n" +
                "Stack Trace:\n" + stackTrace + "\n" +
                "Input Data:\n" + inputLine;
        Prompt prompt = new Prompt(new UserMessage(message));

        chatModel.stream(prompt)
            .map(resp -> resp.getResult().getOutput())
            .map(msg -> msg.getText())
            .reduce(new StringBuilder(), StringBuilder::append)
            .map(StringBuilder::toString)
            .map(r -> formatAiResponse(r, stackTrace))
            .doOnNext(log::error)
            .doOnError(e -> log.error("Error during AI analysis: {}", e.getMessage(), e))
            .subscribe();
    }

    private String formatAiResponse(String aiResponse, String stackTrace) {
        String divider = "\n" + "=".repeat(80) + "\n";
        String mdivider = "\n" + "_".repeat(80) + "\n";
        return 
            divider + "AI Analysis for Stack Trace:\n" + stackTrace 
            + mdivider + "AI Analysis Result:\n" + aiResponse + divider;
    }
}