package com.myexchange.tradingsimulation;
public class ExecutedOrder {
    private double price;
    private int volume;

    public ExecutedOrder(double price, int volume) {
        this.price = price;
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }
}
