package ru.agorbunov.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.agorbunov.restaurant.model.jpa.OrdersDishes;
import ru.agorbunov.restaurant.util.ComparatorUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Admin on 17.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
@NamedNativeQuery(name = Dish.DELETE_FROM_ORDERS, query = "DELETE FROM orders_dishes WHERE dish_id=? AND order_id=?")
@NamedQueries({
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d from Dish d"),
        @NamedQuery(name = Dish.GET_ALL_BY_MENU_LIST, query = "SELECT d from Dish d join fetch d.menuList where d.menuList.id=:menuListId"),
        @NamedQuery(name = Dish.GET_ALL_BY_ORDER, query = "SELECT o from Order o left join fetch o.dishes WHERE o.id=:orderId"),
        @NamedQuery(name = Dish.GET_WITH_ORDERS, query = "SELECT d from Dish d left join fetch d.orders WHERE d.id=:id"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id")
})
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    public static final String GET_ALL = "Dish.getAll";
    public static final String GET_ALL_BY_MENU_LIST = "Dish.getAllByMenuList";
    public static final String GET_ALL_BY_ORDER = "Dish.getAllByOrder";
    public static final String DELETE = "Dish.delete";
    public static final String GET_WITH_ORDERS = "Dish.getWithOrders";
    public static final String DELETE_FROM_ORDERS = "deleteFromOrders";

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_list_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MenuList menuList;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "dish")
    private List<OrdersDishes> orders;

    public Map<Order,Integer> getOrders() {
        Map<Order,Integer> result = new TreeMap<>(ComparatorUtil.orderComparator);
        for (OrdersDishes order : orders){
            result.put(order.getOrder(),order.getDishQuantity());
        }
        return result;
    }

    public void setOrders(Map<Order,Integer> ordersMap) {
        List<OrdersDishes> result = new ArrayList<>();
        for (Map.Entry<Order,Integer> order : ordersMap.entrySet()){
            OrdersDishes element = new OrdersDishes();
            element.setDish(this);
            element.setOrder(order.getKey());
            element.setDishQuantity(order.getValue());
            result.add(element);
        }
        this.orders = result;
    }

    public Dish() {
    }

    public Dish(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MenuList getMenuList() {
        return menuList;
    }

    public void setMenuList(MenuList menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
