package com.example;

import java.util.*;

class OrderVisitor implements VisitorInterface {
  private List<Order> orderObjList;
  private OrderIterator iterator;

  public OrderVisitor() {
    orderObjList = new ArrayList<Order>();
    iterator = new OrderIterator(orderObjList);
  }
  public void visit(NonCaliforniaOrder inp_order) {
    orderObjList.add(inp_order);
  }
  public void visit(CaliforniaOrder inp_order) {
    orderObjList.add(inp_order);
  }
  public void visit(OverseasOrder inp_order) {
    orderObjList.add(inp_order);
  }
  
  public OrderIterator getIterator(){
    return iterator;
  }
}
