package com.myexchange.tradingsimulation;

public class FixingAlgorithmTask implements Runnable {
    private final OrderBook orderBook;

    public FixingAlgorithmTask(OrderBook orderBook) {
        this.orderBook = orderBook;
    }
    @Override
    public void run() {
        OrderBook orderBook = this.orderBook;

        // Obtain the current best bid and ask prices
        double bestBidPrice = orderBook.getBestBidPrice();
        double bestAskPrice = orderBook.getBestAskPrice();

        // Calculate the fixing price as the mid-point between the best bid and ask prices
        double fixingPrice = (bestBidPrice + bestAskPrice) / 2;

        // Set the fixing price for the order book
        orderBook.setFixingPrice(fixingPrice);

        // Log the fixing price
        System.out.println("Fixing price for " + orderBook.getSymbol() + ": " + fixingPrice);
    }

}