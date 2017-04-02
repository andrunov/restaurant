package ru.agorbunov.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.agorbunov.restaurant.model.jpa.OrdersDishes;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 17.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
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

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_list_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MenuList menuList;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "dish")
    private List<OrdersDishes> ordersDishesList;

    public List<OrdersDishes> getOrdersDishesList() {
        return ordersDishesList;
    }

    public void setOrdersDishesList(Map<Order,Integer> ordersDishesMap){
        List<OrdersDishes> result = null;
        for (Map.Entry<Order,Integer> entry : ordersDishesMap.entrySet()){
            OrdersDishes orderDishes = new OrdersDishes();
            orderDishes.setDish(this);
            orderDishes.setOrder(entry.getKey());
            orderDishes.setDishQuantity(entry.getValue());
            result.add(orderDishes);
        }
        this.ordersDishesList = result;
    }




    @ManyToMany
    @JoinTable(
            name="orders_dishes",
            joinColumns=@JoinColumn(name="dish_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="order_id", referencedColumnName="id"))
    private List<Order> orders;

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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
