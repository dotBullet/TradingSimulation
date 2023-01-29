package com.myexchange.tradingsimulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class TradingSimulationApplication {

    private final ConcurrentHashMap<String, OrderBook> orderBooks;
    private final ExecutorService executor;

    public TradingSimulationApplication() {
        this.orderBooks = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(4);
    }

    public static void main(String[] args) {
        SpringApplication.run(TradingSimulationApplication.class, args);
    }

}
