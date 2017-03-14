package ru.agorbunov.restaurant.repository;

import ru.agorbunov.restaurant.model.Dish;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface DishRepository extends BaseRepository<Dish> {

    Dish save(Dish dish,  int menulistId);

    Dish save(Dish dish,  int menulistId, int...ordersIds);

    Dish get(int id, int menuListId);

    Dish getWithOrders(int id, int menuListId);

    List<Dish> getByMenuList(int menuListId);

    List<Dish> getByOrder(int orderId);
}
