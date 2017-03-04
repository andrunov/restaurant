package ru.agorbunov.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 17.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d from Dish d"),
        @NamedQuery(name = Dish.GET_WITH, query = "SELECT d from Dish d left join fetch d.orders WHERE d.id=:id"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id")
})
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    public static final String GET_ALL = "Dish.getAll";
    public static final String DELETE = "Dish.delete";
    public static final String GET_WITH = "Dish.getWith";

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_list_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MenuList menuList;

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
