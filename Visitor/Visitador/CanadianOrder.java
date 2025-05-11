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

  public void accept(OrderVisitor v) {
    v.visit(this);
  }
    
}
