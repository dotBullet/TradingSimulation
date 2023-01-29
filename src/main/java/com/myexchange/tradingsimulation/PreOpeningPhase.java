package com.myexchange.tradingsimulation;

import java.util.concurrent.*;

public class PreOpeningPhase {
    private final ExecutorService executor;
    private final ConcurrentHashMap<String, OrderBook> orderBooks;

    public PreOpeningPhase(ExecutorService executor, ConcurrentHashMap<String, OrderBook> orderBooks) {
        this.executor = executor;
        this.orderBooks = orderBooks;
    }

    public void run(String[] symbols) {
        for (String symbol : orderBooks.keySet()) {
            OrderBook orderBook = orderBooks.get(symbol);
            executor.execute(new FileReaderTask(symbol, "preopening", orderBook));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String symbol : symbols) {
            OrderBook orderBook = orderBooks.get(symbol);
            executor.execute(new FixingAlgorithmTask(orderBook));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}