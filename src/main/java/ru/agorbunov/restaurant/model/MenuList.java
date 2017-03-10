package ru.agorbunov.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Admin on 20.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = MenuList.GET_ALL, query = "SELECT m from MenuList m ORDER BY m.dateTime desc"),
        @NamedQuery(name = MenuList.GET_ALL_BY_RESTAURANT, query = "SELECT m from MenuList m where m.restaurant.id=:restaurantId ORDER BY m.dateTime desc"),
        @NamedQuery(name = MenuList.GET_WITH, query = "SELECT m from MenuList m left join fetch m.dishList where m.id=:id"),
        @NamedQuery(name = MenuList.DELETE, query = "DELETE FROM MenuList m WHERE m.id=:id")
})
@Entity
@Table(name = "menu_lists")
public class MenuList extends BaseEntity {

    public static final String GET_ALL = "MenuList.getAll";
    public static final String GET_ALL_BY_RESTAURANT = "MenuList.getAllbyRestaurant";
    public static final String DELETE = "MenuList.delete";
    public static final String GET_WITH = "MenuList.getWith";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "menuList")
    private List<Dish> dishList;

    @Column(name = "date_time" , nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;

    public MenuList() {
    }

    public MenuList(Restaurant restaurant, LocalDateTime dateTime) {
        this.restaurant = restaurant;
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
                "dateTime=" + dateTime +
                '}';
    }
}
