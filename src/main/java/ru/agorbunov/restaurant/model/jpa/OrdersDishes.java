package ru.agorbunov.restaurant.model.jpa;

import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.Order;

import javax.persistence.*;

/**
 * Created by Admin on 28.03.2017.
 */
@Entity
@Table(name="orders_dishes")
@IdClass(OrdersDishesId.class)
public class OrdersDishes {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private Dish dish;

    @Column(name = "dish_quantity", nullable = false)
    private int dishQuantity;

    public int getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(int dishQuantity) {
        this.dishQuantity = dishQuantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
