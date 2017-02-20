package ru.agorbunov.restaurant.model;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 17.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = User.GET_ALL, query = "SELECT u from User u"),
        @NamedQuery(name = User.GET_WITH, query = "SELECT u from User u LEFT JOIN FETCH u.orders WHERE u.id = :id"),
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id")
})
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    public static final String GET_ALL = "User.getAll";
    public static final String GET_WITH = "User.getWith";
    public static final String DELETE = "User.delete";

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order> orders;


    public User() {
    }

    public User(String name, String email, String password, List<Order> orders, Role role, Role... roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = EnumSet.of(role, roles);
        this.orders = orders;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
