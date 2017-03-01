package ru.agorbunov.restaurant.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 17.01.2017.
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r from Restaurant r"),
        @NamedQuery(name = Restaurant.GET_WITH_MENU_LISTS, query = "SELECT r from Restaurant r left join fetch r.menuLists WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.GET_WITH_ORDERS, query = "SELECT r from Restaurant r left join fetch r.orders WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {

    public static final String GET_ALL = "Restaurant.getAll";
    public static final String GET_WITH_MENU_LISTS = "Restaurant.getWithMenuLists";
    public static final String GET_WITH_ORDERS = "Restaurant.getWithOrders";
    public static final String DELETE = "Restaurant.delete";

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<MenuList> menuLists;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private  List<Order> orders;

    public Restaurant() {
    }

    public Restaurant(String name, String address, List<MenuList> menuLists, List<Order> orders) {
        this.name = name;
        this.address = address;
        this.menuLists = menuLists;
        this.orders = orders;
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MenuList> getMenuLists() {
        return menuLists;
    }

    public void setMenuLists(List<MenuList> menuLists) {
        this.menuLists = menuLists;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addMenuList(MenuList menuList){
        this.menuLists.add(menuList);
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
