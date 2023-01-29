package com.myexchange.tradingsimulation;

public class ClosingAlgorithmTask implements Runnable {
    private final OrderBook orderBook;

    public ClosingAlgorithmTask(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    public void run() {
        // Implement your closing algorithm here
        OrderBook orderBook = this.orderBook;

        // Obtain the current best bid and ask prices
        double bestBidPrice = orderBook.getBestBidPrice();
        double bestAskPrice = orderBook.getBestAskPrice();

        // Calculate the closing price as the average of the best bid and ask prices
        double closingPrice = (bestBidPrice + bestAskPrice) / 2;

        // Set the closing price for the order book
        orderBook.setClosingPrice(closingPrice);

        // Log the closing price
        System.out.println("Closing price for " + orderBook.getSymbol() + ": " + closingPrice);
    }

}

