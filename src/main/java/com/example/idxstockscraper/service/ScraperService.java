package com.example.idxstockscraper.service;

import com.example.idxstockscraper.model.DailyStockTransaction;
import com.example.idxstockscraper.model.Stock;
import com.example.idxstockscraper.util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {
    @Autowired
    Util util;

    @Autowired
    RepositoryService repositoryService;

    public String scrapeData() {
        try {
            List<Stock> newlyFetchedStockData = new ArrayList<>();
            List<DailyStockTransaction> dailyStockTransactions = new ArrayList<>();

            Document doc = Jsoup.connect("https://www.idxchannel.com/market-stock").get();
            Elements tr = doc.body().select("tr");
            for (int i = 1; i < tr.size(); i++) {
                Stock stock = new Stock(tr.get(i).getElementsByTag("td").get(1).text(),
                        tr.get(i).getElementsByTag("td").get(2).text());
                newlyFetchedStockData.add(stock);

                DailyStockTransaction dailyStockTransaction = new DailyStockTransaction();
                dailyStockTransaction.setStock(stock);
                dailyStockTransaction.setOpen(Integer.parseInt(tr.get(i).getElementsByTag("td").get(3).text()));
                dailyStockTransaction.setClose(Integer.parseInt(tr.get(i).getElementsByTag("td").get(4).text()));
                dailyStockTransaction.setChange(Integer.parseInt(tr.get(i).getElementsByTag("td").get(5).text()));
                dailyStockTransaction.setChangePercentage(Float.parseFloat(tr.get(i).getElementsByTag("td").get(6).text().replace("%", "")));
                dailyStockTransactions.add(dailyStockTransaction);
            }

            // Filter new stock
            List<Stock> currentStockData = repositoryService.retrieveAllStockData();
            List<Stock> newStockList = util.filterNewStock(newlyFetchedStockData, currentStockData);

            // Save new stock if any
            if (!newStockList.isEmpty()) {
                // Save new stock list
                repositoryService.saveStockData(newStockList);
            }

            // Save dailyStockTransactions
            repositoryService.saveDailyStockTransactionData(dailyStockTransactions);

            return "Scraper program is finished";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
