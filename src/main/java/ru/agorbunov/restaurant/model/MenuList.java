package ru.agorbunov.restaurant.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Admin on 20.01.2017.
 */
@Entity
@Table(name = "menu_lists")
public class MenuList extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    private List<Dish> dishList;

    @Column(name = "date_time" , nullable = false)
    private LocalDateTime dateTime;

    public MenuList() {
    }

    public MenuList(Restaurant restaurant, List<Dish> dishList, LocalDateTime dateTime) {
        this.restaurant = restaurant;
        this.dishList = dishList;
        this.dateTime = dateTime;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "MenuList{" +
                "restaurant=" + restaurant +
                ", dishList=" + dishList +
                '}';
    }

}
