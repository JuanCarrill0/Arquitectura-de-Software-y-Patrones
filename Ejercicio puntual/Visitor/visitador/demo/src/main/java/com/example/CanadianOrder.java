package com.example;

public class CanadianOrder implements Order {
    private double orderAmount;

    public CanadianOrder() {
    }

    public CanadianOrder(double inp_orderAmount) {
        orderAmount = inp_orderAmount;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void accept(OrderVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return "CanadianOrder{" + "orderAmount=" + orderAmount + '}';
    }
}
