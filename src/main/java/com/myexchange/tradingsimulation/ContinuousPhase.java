package com.myexchange.tradingsimulation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


public class ContinuousPhase {
    private final ExecutorService executor;
    private final ConcurrentHashMap<String, OrderBook> orderBooks;

    public ContinuousPhase(ExecutorService executor, ConcurrentHashMap<String, OrderBook> orderBooks) {
        this.executor = executor;
        this.orderBooks = orderBooks;
    }

        public void run() {
            for (OrderBook orderBook : orderBooks.values()) {
                executor.execute(new MatchingAlgorithmTask(orderBook));
            }
            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}