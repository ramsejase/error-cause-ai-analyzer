package com.rj.analyzer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rj.analyzer.service.CsvProcessorService;

@RestController
public class ErrorAnalysisController {

    private final CsvProcessorService csvProcessorService;

    public ErrorAnalysisController(CsvProcessorService csvProcessorService) {
        this.csvProcessorService = csvProcessorService;
    }

    @GetMapping("/run")
    public String analyzeErrors() throws Exception {
         csvProcessorService.processCsvData();
        return "CSV processing and error analysis initiated. Check the console logs for AI insights.";
    }
}
