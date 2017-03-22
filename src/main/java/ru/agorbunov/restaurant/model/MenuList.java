package ru.agorbunov.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.agorbunov.restaurant.util.DateTimeUtil;

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
        @NamedQuery(name = MenuList.GET_WITH_DISHES, query = "SELECT m from MenuList m left join fetch m.dishList where m.id=:id"),
        @NamedQuery(name = MenuList.DELETE, query = "DELETE FROM MenuList m WHERE m.id=:id")
})
@Entity
@Table(name = "menu_lists")
public class MenuList extends BaseEntity {

    public static final String GET_ALL = "MenuList.getAll";
    public static final String GET_ALL_BY_RESTAURANT = "MenuList.getAllbyRestaurant";
    public static final String DELETE = "MenuList.delete";
    public static final String GET_WITH_DISHES = "MenuList.getWithDishes";

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "menuList")
    private List<Dish> dishList;

    @Column(name = "date_time" , nullable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateTime;

    public MenuList() {
    }

    public MenuList(Restaurant restaurant, String description, LocalDateTime dateTime) {
        this.restaurant = restaurant;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MenuList{" +
                "description='" + description + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
