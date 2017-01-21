package ru.agorbunov.restaurant.model;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Admin on 17.01.2017.
 */
public class User extends BaseEntity {

    private String name;

    private String email;

    private String password;

    private Set<Role> roles;

    public User() {
    }

    public User(String name, String email, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public User(String name, String email, Role role, Role... roles){
        this.name = name;
        this.email = email;
        this.roles = EnumSet.of(role, roles);
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

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
