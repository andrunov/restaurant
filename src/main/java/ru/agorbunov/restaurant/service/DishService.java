package ru.agorbunov.restaurant.service;

import ru.agorbunov.restaurant.model.Dish;

/**
 * Created by Admin on 30.01.2017.
 */
public interface DishService extends BaseService<Dish> {

    Dish save(Dish dish,  int menulistId, int...ordersIds);

    Dish get(int id, int menulistId);

    Dish getWith(int id, int menulistId);
}
