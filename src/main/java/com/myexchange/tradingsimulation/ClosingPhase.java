package com.myexchange.tradingsimulation;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ClosingPhase {
    private final ExecutorService executor;
    private final ConcurrentHashMap<String, OrderBook> orderBooks;

    public ClosingPhase(ExecutorService executor, ConcurrentHashMap<String, OrderBook> orderBooks) {
        this.executor = executor;
        this.orderBooks = orderBooks;
    }

    public void run() {
        for (String symbol : orderBooks.keySet()) {
            OrderBook orderBook = orderBooks.get(symbol);
            executor.execute(new FileReaderTask(symbol, "preclosing", orderBook));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (OrderBook orderBook : orderBooks.values()) {
            executor.execute(new ClosingAlgorithmTask(orderBook));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}