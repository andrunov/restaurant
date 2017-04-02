package ru.agorbunov.restaurant.model.jpa;

import java.io.Serializable;

/**
 * Created by Admin on 28.03.2017.
 */
public class OrdersDishesId implements Serializable {

    private int order;

    private int dish;

    public OrdersDishesId() {
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDish() {
        return dish;
    }

    public void setDish(int dish) {
        this.dish = dish;
    }
}
