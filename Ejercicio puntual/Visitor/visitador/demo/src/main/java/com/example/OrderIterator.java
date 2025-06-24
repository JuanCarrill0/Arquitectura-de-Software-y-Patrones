package com.example;

import java.util.List;
import java.util.NoSuchElementException;

public class OrderIterator implements java.util.Iterator<Order> {

    private final List<Order> orders;
    private int currentIndex = 0;
    private int lastReturnedIndex = -1;

    public OrderIterator(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < orders.size();
    }

    @Override
    public Order next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastReturnedIndex = currentIndex;
        return orders.get(currentIndex++);
    }

    @Override
    public void remove() {
        if (lastReturnedIndex < 0) {
            throw new IllegalStateException("next() must be called before remove()");
        }
        orders.remove(lastReturnedIndex);
        // Ajustamos índice porque eliminamos un elemento anterior
        if (lastReturnedIndex < currentIndex) {
            currentIndex--;
        }
        lastReturnedIndex = -1;
    }

    public void reset() {
        currentIndex = 0;
        lastReturnedIndex = -1;
    }
}
