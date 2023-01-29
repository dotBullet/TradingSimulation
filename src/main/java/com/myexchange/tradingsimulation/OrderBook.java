package com.myexchange.tradingsimulation;

import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ConcurrentHashMap;

public class OrderBook {
    private final String symbol;
    private final ConcurrentHashMap<Double, Integer> bids;
    private final ConcurrentHashMap<Double, Integer> asks;

    private double fixingPrice;
    public String getSymbol() {
        return symbol;
    }

    public ConcurrentHashMap<Double, Integer> getBids() {
        return bids;
    }

    public ConcurrentHashMap<Double, Integer> getAsks() {
        return asks;
    }
    private double bestBidPrice;
    private double bestAskPrice;
    private double closingPrice;

    public OrderBook(String symbol) {
        this.symbol = symbol;
        this.bids = new ConcurrentHashMap<>();
        this.asks = new ConcurrentHashMap<>();
    }

    public double getBestBidPrice() {
        return bestBidPrice;
    }

    public double getBestAskPrice() {
        return bestAskPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }
    public void setFixingPrice(double fixingPrice) {
        this.fixingPrice = fixingPrice;
    }


    private List<ExecutedOrder> executedOrders = new ArrayList<>();
    public void addExecutedOrder(double price, int volume) {
        executedOrders.add(new ExecutedOrder(price, volume));
    }


}
