package com.myexchange.tradingsimulation;

public class ConcreteOrder extends Order {
    private int quantity;
    private double price;

    public ConcreteOrder(double price, int quantity, Side side) {
        super(price, quantity, side);
        this.quantity = quantity;
        this.price = price;
    }


    @Override
    public int value() {
        return getVolume();
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConcreteOrder that = (ConcreteOrder) o;

        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (getVolume() != that.getVolume()) return false;
        return getSide() == that.getSide();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getPrice());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + getVolume();
        result = 31 * result + (getSide() != null ? getSide().hashCode() : 0);
        return result;
    }
}
