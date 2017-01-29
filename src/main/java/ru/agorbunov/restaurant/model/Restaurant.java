package ru.agorbunov.restaurant.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 17.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r from Restaurant r"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {

    public static final String GET_ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<MenuList> menuLists;

    public Restaurant() {
    }

    public Restaurant(String name, String address, List<MenuList> menuLists) {
        this.name = name;
        this.address = address;
        this.menuLists = menuLists;
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
