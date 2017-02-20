package ru.agorbunov.restaurant.repository;

/**
 * Created by Admin on 04.02.2017.
 */
public interface UserAndRestaurantRepository<T> extends BaseRepository {

    T save(T tEntity);

    T get(int id);

    T getWith(int id);
}
