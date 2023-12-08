package com.example.idxstockscraper.service;

import com.example.idxstockscraper.model.DailyStockTransaction;
import com.example.idxstockscraper.model.Stock;
import com.example.idxstockscraper.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RepositoryService {
    @Autowired
    Util util;

    public String saveStockData(List<Stock> stocks) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String url = "http://127.0.0.1:8000/DataManagementAPI/saveAllStockData";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(stocks), headers);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, request, String.class);
            return "Stock data was saved";
        } catch (RestClientException e) {
            throw new Exception("Failed to save daily stock transaction data...");
        }
    }

    public List<Stock> retrieveAllStockData() throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String url = "http://127.0.0.1:8000/DataManagementAPI/retrieveAllStockData";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(response, new TypeReference<List<Stock>>() {});
        } catch (RestClientException e) {
            throw new Exception("Failed to save daily stock transaction data...");
        }
    }

    public String saveDailyStockTransactionData(List<DailyStockTransaction> dailyStockTransactions) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String url = "http://127.0.0.1:8000/DataManagementAPI/saveAllDailyStockTransaction";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            List<Map<String, String>> reformattedDailyStockTransactionData = util.reformatDailyStockTransactionData(dailyStockTransactions);

            HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(reformattedDailyStockTransactionData), headers);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, request, String.class);
            return "Daily stock transaction data was saved";
        } catch (RestClientException e) {
            throw new Exception("Failed to save daily stock transaction data...");
        }
    }
}
