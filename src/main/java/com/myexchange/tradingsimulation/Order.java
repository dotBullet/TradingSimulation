package com.myexchange.tradingsimulation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public abstract class Order {
    private final double price;
    private final int volume;
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    private final Side side;
    enum Side {
        BID,
        ASK
    }

    public Order(double price, int volume, Side side) {
        this.price = price;
        this.volume = volume;
        this.side = side;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public Side getSide() {
        return side;
    }

    public abstract int value();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.price, price) == 0 &&
                volume == order.volume &&
                side == order.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, volume, side);
    }
}

