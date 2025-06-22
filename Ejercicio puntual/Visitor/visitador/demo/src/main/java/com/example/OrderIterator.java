package com.example;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderIterator implements Iterator<Order>{
    
    private List<Order> orders;
    private Iterator<Order> it;
    private Order nextOrder;
    private Order lastReturned;


    public OrderIterator(List<Order> orders){
        this.orders = orders;
        it = this.orders.iterator();
    }

    public boolean hasNext(){
        nextOrder = null;

        while (it.hasNext()) {
            Order temp = it.next();
            nextOrder = temp;
            break;
        }

        return nextOrder != null;
    }

    public Order next(){
        if (nextOrder == null) {
            throw new NoSuchElementException();
        }
        lastReturned = nextOrder;
        nextOrder = null;
        return lastReturned;
    }

    public void remove(){
         if (lastReturned == null) {
            throw new IllegalStateException("next() must be called before remove()");
        }
        orders.remove(lastReturned);
        reset();
        lastReturned = null;
    }

    public void reset() {
        this.it = orders.iterator();
        this.nextOrder = null;
        this.lastReturned = null;
    }
}
