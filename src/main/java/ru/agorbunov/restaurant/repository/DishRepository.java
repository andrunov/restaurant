package ru.agorbunov.restaurant.repository;

import ru.agorbunov.restaurant.model.Dish;

/**
 * Created by Admin on 30.01.2017.
 */
public interface DishRepository extends BaseRepository<Dish> {

    Dish save(Dish dish,  int menulistId, int...ordersIds);

    Dish get(int id, int userId);
}
