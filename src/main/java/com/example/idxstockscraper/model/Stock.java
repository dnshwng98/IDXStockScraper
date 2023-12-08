package com.example.idxstockscraper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {
    private String code;
    private String name;
    private double relativeDailyPerformance = 0.0;

    private double daily_return = 0.0;

    public Stock(){}
    public Stock(String code, String name){
        this.code = code;
        this.name = name;
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRelativeDailyPerformance() {
        return relativeDailyPerformance;
    }

    public void setRelativeDailyPerformance(double relativeDailyPerformance) {
        this.relativeDailyPerformance = relativeDailyPerformance;
    }

    public double getDaily_return() {
        return daily_return;
    }

    public void setDaily_return(double daily_return) {
        this.daily_return = daily_return;
    }
}
