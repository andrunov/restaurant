package ru.agorbunov.restaurant.repository;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface ReferenseRepository<T>{
    T save(T tEntity, int id);

    boolean delete(int id);

    List<T> getAll();

    T get(int id, int referenseId);

}
