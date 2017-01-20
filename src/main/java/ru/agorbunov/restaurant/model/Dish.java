package ru.agorbunov.restaurant.model;

import org.springframework.stereotype.Component;

/**
 * Created by Admin on 17.01.2017.
 */
public class Dish extends BaseEntity {

    private String description;

    private Integer price;

    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String description, Integer price, Restaurant restaurant) {
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
