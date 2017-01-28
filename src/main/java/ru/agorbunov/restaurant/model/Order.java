package ru.agorbunov.restaurant.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Admin on 17.01.2017.
 */
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private List<Dish> dishes;

    @Column(name = "date_time" , nullable = false)
    private LocalDateTime dateTime;

    public Order() {
    }

    public Order(User user, Restaurant restaurant, List<Dish> dishes, LocalDateTime dateTime) {
        this.user = user;
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                ", dishes=" + dishes +
                ", dateTime=" + dateTime +
                '}';
    }
}
