package ru.agorbunov.restaurant.service;

import java.util.List;

/**
 * Created by Admin on 27.01.2017.
 */
public interface BaseService<T> {

    void delete(int id);

    List<T> getAll();

}
