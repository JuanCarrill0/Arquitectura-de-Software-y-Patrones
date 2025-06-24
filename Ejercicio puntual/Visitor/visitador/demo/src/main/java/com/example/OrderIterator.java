package com.example;

import java.util.Iterator;
import java.util.List;

public class OrderIterator implements Iterator<Order>{
    
    private List<Order> orders;
    private Iterator<Order> it;
    private Order lastReturned;


    public OrderIterator(List<Order> orders){
        this.orders = orders;
        it = this.orders.iterator();
    }

    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public Order next() {
        lastReturned = it.next();
        return lastReturned;
    }

    @Override
    public void remove() {
        if (lastReturned == null) {
            throw new IllegalStateException("next() must be called before remove()");
        }
        it.remove();
        lastReturned = null;
    }

    public void reset() {
        this.it = orders.iterator();
        this.lastReturned = null;
    }
}
