package com.example.idxstockscraper.util;

import com.example.idxstockscraper.model.DailyStockTransaction;
import com.example.idxstockscraper.model.Stock;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Util {
    public List<Stock> filterNewStock(List<Stock> newlyFetchedStockData, List<Stock> currentStockData) throws Exception {
        try {

            return newlyFetchedStockData;
        } catch (Exception e) {
            throw new Exception("Failed to filter new stock");
        }
    }

    public List<Map<String, String>> reformatDailyStockTransactionData(List<DailyStockTransaction> dailyStockTransactions) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, String>> reformattedDailyStockTransactionDataList = new ArrayList<>();
        for (DailyStockTransaction dailyStockTransaction : dailyStockTransactions) {
            Map<String, String> reformattedDailyStockTransactionData = new HashMap<>();

            reformattedDailyStockTransactionData.put("code", dailyStockTransaction.getStock().getCode());
            reformattedDailyStockTransactionData.put("name", dailyStockTransaction.getStock().getName());
            reformattedDailyStockTransactionData.put("open", String.valueOf(dailyStockTransaction.getOpen()));
            reformattedDailyStockTransactionData.put("close", String.valueOf(dailyStockTransaction.getClose()));
            reformattedDailyStockTransactionData.put("change", String.valueOf(dailyStockTransaction.getChange()));
            reformattedDailyStockTransactionData.put("changePercentage", String.valueOf(dailyStockTransaction.getChangePercentage()));
            reformattedDailyStockTransactionData.put("transactionDatetime", dateFormat.format(date));

            reformattedDailyStockTransactionDataList.add(reformattedDailyStockTransactionData);
        }

        return reformattedDailyStockTransactionDataList;
    }
}