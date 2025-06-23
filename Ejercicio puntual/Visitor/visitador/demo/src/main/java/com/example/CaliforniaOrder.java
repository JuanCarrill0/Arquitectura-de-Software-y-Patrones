package com.example;

public class CaliforniaOrder implements Order {
  private double orderAmount;
  private double additionalTax;

  public CaliforniaOrder() {
  }
  public CaliforniaOrder(double inp_orderAmount,
      double inp_additionalTax) {
    orderAmount = inp_orderAmount;
    additionalTax = inp_additionalTax;
  }
  public double getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(double orderAmount) {
    this.orderAmount = orderAmount;
  }

  public double getAdditionalTax() {
    return additionalTax;
  }

  public void setAdditionalTax(double additionalTax) {
    this.additionalTax = additionalTax;
  }
  
  public void accept(OrderVisitor v) {
    v.visit(this);
  }

  @Override
  public String toString() {  
    return "CaliforniaOrder{" + "orderAmount=" + orderAmount + ", additionalTax=" + additionalTax + '}';
  }
}

