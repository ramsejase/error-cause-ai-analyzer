package com.rj.analyzer.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.rj.analyzer.llm.LlmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CsvProcessorService {

    private final LlmService llmService;
    private final ResourceLoader resourceLoader;
    private final Random random = new Random();

    public CsvProcessorService(@Qualifier("ollamaLlmService") LlmService llmService, // Or @Qualifier("openAiLlmService")
                               ResourceLoader resourceLoader) {
        this.llmService = llmService;
        this.resourceLoader = resourceLoader;
    }

    public void processCsvData() throws Exception {
        // Load the CSV file from src/main/resources
        Resource resource = resourceLoader.getResource("classpath:data.csv");
        log.info("Processing CSV file: {}", resource.getFilename());
        
        Path filePath = resource.getFile().toPath();
        Files.lines(filePath).forEach(line -> {
            try {
                int errorType = random.nextInt(4);
                switch (errorType) {
                    case 0 -> {
                        if (random.nextBoolean()) {
                            Integer.parseInt("not-a-number");
                        }
                    }
                    case 1 -> {
                        if (random.nextBoolean()) {
                            String nullString = null;
                            nullString.length();
                        }
                    }
                    case 2 -> {
                        if (random.nextBoolean()) {
                            int[] arr = new int[]{};
                            System.out.println(arr);
                        }
                    }
                    case 3 -> {
                        if (random.nextBoolean()) {
                            throw new IllegalArgumentException("Invalid data format in line.");
                        }
                    }
                }
                // log.info("-----------------------------\nInput Line:\n{}\nStatus: Line processed successfully (no error)\n", line);
            } catch (Exception e) {
                log.error("Error processing line: {}, {}", line, e.getMessage());
                String stackTrace = getStackTrace(e);
                llmService.analyzeError(stackTrace, line);
            }
        });
    }
    
    private String getStackTrace(Exception e) {
        // Get the full stack trace as an array of lines
        String[] stackLines = ExceptionUtils.getStackTrace(e).split("\\r?\\n");
        // Limit to the first 3 lines
        return String.join("\n", java.util.Arrays.copyOfRange(stackLines, 0, Math.min(3, stackLines.length)));
    }
}