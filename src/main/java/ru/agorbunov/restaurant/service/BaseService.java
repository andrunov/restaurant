package ru.agorbunov.restaurant.service;

import java.util.List;

/**
 * Created by Admin on 27.01.2017.
 */
public interface BaseService<T> {

//    T save(T tEntity);

    void delete(int id);

    List<T> getAll();

//    T get(int id);
}
