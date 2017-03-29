package ru.agorbunov.restaurant.model.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Admin on 28.03.2017.
 */
@Embeddable
public class OrdersDishesId implements Serializable {

    @Column(name="order_id")
    private int orderId;

    @Column(name="dish_id")
    private int dishId;
}
