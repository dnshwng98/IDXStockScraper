package com.example.idxstockscraper.controller;

import com.example.idxstockscraper.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScraperController {
    @Autowired
    ScraperService scraperService;

    @GetMapping("/scrapeData")
    public String scrapeData() {
        return scraperService.scrapeData();
    }
}
