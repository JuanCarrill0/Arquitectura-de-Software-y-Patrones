package com.example;

public class OverseasOrder implements Order {
  private double orderAmount;
  private double additionalSH;

  public OverseasOrder() {
  }
  public OverseasOrder(double inp_orderAmount,
      double inp_additionalSH) {
    orderAmount = inp_orderAmount;
    additionalSH = inp_additionalSH;
  }
  public double getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(double orderAmount) {
    this.orderAmount = orderAmount;
  }

  public double getAdditionalSH() {
    return additionalSH;
  }

  public void setAdditionalSH(double additionalSH) {
    this.additionalSH = additionalSH;
  }

  public void accept(OrderVisitor v) {
    v.visit(this);
  }

  @Override
  public String toString() {  
    return "OverseasOrder{" +"orderAmount=" + orderAmount +", additionalSH=" + additionalSH +'}';
  } 
}
