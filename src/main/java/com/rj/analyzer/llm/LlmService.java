package com.rj.analyzer.llm;

public interface LlmService {
    void analyzeError(String stackTrace, String inputLine);
}