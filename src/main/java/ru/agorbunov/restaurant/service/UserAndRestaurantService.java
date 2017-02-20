package ru.agorbunov.restaurant.service;

/**
 * Created by Admin on 04.02.2017.
 */
public interface UserAndRestaurantService<T> extends BaseService {

    T save(T tEntity);

    T update(T tEntity);

    T get(int id);

    T getWith(int id);
}
