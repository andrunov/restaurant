package ru.agorbunov.restaurant.service;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface ReferenseService<T> {
    T save(T tEntity, int id);

    void delete(int id);

    List<T> getAll();

    T get(int id, int referenseId);
}
