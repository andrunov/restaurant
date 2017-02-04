package ru.agorbunov.restaurant.repository;

import java.util.List;

/**
 * Created by Admin on 28.01.2017.
 */
public interface BaseRepository<T> {

    boolean delete(int id);

    List<T> getAll();

}
