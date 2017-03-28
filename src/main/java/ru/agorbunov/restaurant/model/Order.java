package ru.agorbunov.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.agorbunov.restaurant.model.jpa.OrdersDishes;
import ru.agorbunov.restaurant.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 17.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Order.GET_ALL, query = "SELECT o from Order o order by o.dateTime desc "),
        @NamedQuery(name = Order.GET_ALL_BY_USER, query = "SELECT o from Order o join fetch o.restaurant where o.user.id=:userId order by o.dateTime desc "),
        @NamedQuery(name = Order.GET_WITH_DISHES, query = "SELECT o from Order o left join fetch o.ordersDishesList where o.id=:id"),
        @NamedQuery(name = Order.DELETE, query = "DELETE FROM Order o WHERE o.id=:id")
})
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    public static final String GET_ALL = "Order.getAll";
    public static final String GET_ALL_BY_USER = "Order.getAllbyUser";
    public static final String DELETE = "Order.delete";
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
    private List<OrdersDishes> ordersDishesList;

//    @ManyToMany
//    @JoinTable(
//            name = "orders_dishes",
//            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"))
//    protected List<Dish> dishes;
//    private Map<Dish,Integer> dishes;

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

    public List<OrdersDishes> getOrdersDishesList() {
        return ordersDishesList;
    }

    public void setOrdersDishesList(List<OrdersDishes> ordersDishesList) {
        this.ordersDishesList = ordersDishesList;
    }

    public Map<Dish, Integer> getDishes() {
        Map<Dish,Integer> result = new LinkedHashMap();
        for (OrdersDishes ordersDishes : ordersDishesList){
            result.put(ordersDishes.getDish(),ordersDishes.getDishQuantity());
        }
        return result;
    }

    public void setDishes(Map<Dish, Integer> dishes) {
        List<OrdersDishes> result = new ArrayList<>();
        for (Map.Entry<Dish,Integer> dishEntry : dishes.entrySet()){
            OrdersDishes orderDishes = new OrdersDishes();
            orderDishes.setDish(dishEntry.getKey());
            orderDishes.setDishQuantity(dishEntry.getValue());
            result.add(orderDishes);
        }
        this.ordersDishesList = result;
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
