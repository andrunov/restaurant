package ru.agorbunov.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Admin on 17.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Order.GET_ALL, query = "SELECT o from Order o"),
        @NamedQuery(name = Order.GET_WITH, query = "SELECT o from Order o left join fetch o.dishes where o.id=:id"),
        @NamedQuery(name = Order.DELETE, query = "DELETE FROM Order o WHERE o.id=:id")
})
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    public static final String GET_ALL = "Order.getAll";
    public static final String DELETE = "Order.delete";
    public static final String GET_WITH = "Order.getWith";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(
            name="orders_dishes",
            joinColumns=@JoinColumn(name="order_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="dish_id", referencedColumnName="id"))
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
                "dateTime=" + dateTime +
                '}';
    }
}
