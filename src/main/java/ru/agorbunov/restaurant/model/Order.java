package ru.agorbunov.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.agorbunov.restaurant.model.jpa.OrdersDishes;
import ru.agorbunov.restaurant.util.ComparatorUtil;
import ru.agorbunov.restaurant.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Admin on 17.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
@NamedNativeQuery(name = Order.GET_ALL_BY_DISH, query = "SELECT o.* FROM orders AS o LEFT JOIN orders_dishes AS od ON o.id = od.order_id WHERE od.dish_id=? ORDER BY date_time DESC ",resultClass = Order.class)
@NamedQueries({
        @NamedQuery(name = Order.GET_ALL, query = "SELECT o from Order o order by o.dateTime desc "),
        @NamedQuery(name = Order.GET_ALL_BY_USER, query = "SELECT o from Order o join fetch o.restaurant where o.user.id=:userId order by o.dateTime desc "),
        @NamedQuery(name = Order.GET_WITH_DISHES, query = "SELECT o from Order o left join fetch o.dishes where o.id=:id"),
        @NamedQuery(name = Order.DELETE, query = "DELETE FROM Order o WHERE o.id=:id"),
        @NamedQuery(name = Order.DELETE_ORDERS_DISHES, query = "DELETE FROM OrdersDishes od WHERE od.order.id=:id")
})
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    public static final String GET_ALL = "Order.getAll";
    public static final String GET_ALL_BY_USER = "Order.getAllbyUser";
    public static final String GET_ALL_BY_DISH = "Order.getAllbyDish";
    public static final String DELETE = "Order.delete";
    public static final String DELETE_ORDERS_DISHES = "Order.deleteOrdersDishes";
    public static final String GET_WITH_DISHES = "Order.getWithDishes";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "order")
    private List<OrdersDishes> dishes;

    @Column(name = "date_time" , nullable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateTime;

    public Order() {
    }

    public Order(User user, Restaurant restaurant, LocalDateTime dateTime) {
        this.user = user;
        this.restaurant = restaurant;
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

    public Map<Dish, Integer> getDishes() {
        Map<Dish,Integer> result = new TreeMap<>(ComparatorUtil.dishComparator);
        for (OrdersDishes dish : dishes){
            result.put(dish.getDish(), dish.getDishQuantity());
        }
        return result;
    }

    public void setDishes(Map<Dish, Integer> dishes) {
        List<OrdersDishes> result = new ArrayList<>();
        for (Map.Entry<Dish,Integer> dish : dishes.entrySet()){
            OrdersDishes orderDishes = new OrdersDishes();
            orderDishes.setOrder(this);
            orderDishes.setDish(dish.getKey());
            orderDishes.setDishQuantity(dish.getValue());
            result.add(orderDishes);
        }
        this.dishes = result;
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
