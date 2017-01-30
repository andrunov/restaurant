package ru.agorbunov.restaurant.service;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface ListServise<T> {

    T save(T order,  int id, int...ids);

    void delete(int id);

    List<T> getAll();

    T get(int id, int userId);
}
