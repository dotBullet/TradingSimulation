package com.myexchange.tradingsimulation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MatchingAlgorithmTask implements Runnable {
    private final OrderBook orderBook;

    public MatchingAlgorithmTask(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    public void run() {
        List<ConcreteOrder> bidOrders = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : orderBook.getBids().entrySet()) {
            bidOrders.add(new ConcreteOrder(entry.getKey(), entry.getValue(), Order.Side.BID));
        }

        List<ConcreteOrder> askOrders = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : orderBook.getAsks().entrySet()) {
            askOrders.add(new ConcreteOrder(entry.getKey(), entry.getValue(), Order.Side.ASK));
        }

        bidOrders.sort(Comparator.comparingDouble(ConcreteOrder::getPrice).reversed());
        askOrders.sort(Comparator.comparingDouble(ConcreteOrder::getPrice));

        int bidIndex = 0;
        int askIndex = 0;
        while (bidIndex < bidOrders.size() && askIndex < askOrders.size()) {
            ConcreteOrder bid = bidOrders.get(bidIndex);
            ConcreteOrder ask = askOrders.get(askIndex);

            if (bid.getPrice() >= ask.getPrice()) {
                int executedVolume = Math.min(bid.value(), ask.value());
                matchExecution(bid, ask, executedVolume);

                if (bid.value() == executedVolume) {
                    bidIndex++;
                }
                if (ask.value() == executedVolume) {
                    askIndex++;
                }
            } else {
                break;
            }
        }
    }

    private void matchExecution(ConcreteOrder bid, ConcreteOrder ask, int executedVolume) {
        bid.setQuantity(bid.value() - executedVolume);
        ask.setQuantity(ask.value() - executedVolume);

        orderBook.addExecutedOrder(bid.getPrice(), executedVolume);
    }
}
