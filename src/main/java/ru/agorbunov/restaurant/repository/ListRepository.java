package ru.agorbunov.restaurant.repository;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface ListRepository<T> {

    T save(T order,  int id, int...ids);

    boolean delete(int id);

    List<T> getAll();

    T get(int id, int userId);
}
