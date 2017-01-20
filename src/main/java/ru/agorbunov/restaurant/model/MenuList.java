package ru.agorbunov.restaurant.model;

import java.util.List;

/**
 * Created by Admin on 20.01.2017.
 */
public class MenuList extends BaseEntity {

    private Restaurant restaurant;

    private List<Dish> dishList;

    public MenuList() {
    }

    public MenuList(Restaurant restaurant, List<Dish> dishList) {
        this.restaurant = restaurant;
        this.dishList = dishList;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    @Override
    public String toString() {
        return "MenuList{" +
                "restaurant=" + restaurant +
                ", dishList=" + dishList +
                '}';
    }

}
